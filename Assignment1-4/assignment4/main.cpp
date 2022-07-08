//Author:   Gregorics Tibor
//Date:     2017.08.31.
//Title:    (1) Is there an angler who has caught only and at least three catfishes on one of his contests？
//              Give the contest id. too
//          (2) How many contests there are where at least one angler has caught only and at least three catfishes？

#include <iostream>
#include <sstream>
#include "./library/counting.hpp"
#include "./library/linsearch.hpp"
#include "./library/summation.hpp"
#include "./library/seqinfileenumerator.hpp"
#include "./library/stringstreamenumerator.hpp"

using namespace std;

//type of fish
struct Fish{
    string species;
    int    size;
    bool isCatfish;
};

//type of contest
struct Contest
{
	string anglerName;
    string contestID;
    bool hasOnlyAtLeastThreeCatfish;
};

//enumerator of contest recorded in text file
//overrides the method first(), next(), current(), end()
class ContestEnumerator : public Enumerator<Contest> {
protected:
    SeqInFileEnumerator<Contest> *_f;
    Contest _current;
    bool _end;
public:
    ContestEnumerator(const string& str)
        { _f = new SeqInFileEnumerator< Contest>(str); }
    ~ ContestEnumerator(){ delete _f; }
    void first()         override { _f->first(); next(); }
    void next()          override;
    bool end()     const override { return _end; }
    Contest current() const override { return _current; }
};

//class of counting of ingredients including sugar
//overrides the method cond()
class ContestCounting : public Counting<Contest>{
protected:
    bool cond(const Contest &e) const { return e.hasOnlyAtLeastThreeCatfish; }
};

//Activity: writes the selected anglers and the number of contests where at least one angler has caught only and at least three catfishes
int main()
{
    string filename;
    cout<<"Enter the name of the input file, please: ";
    cin>>filename;

    // check if file exists
    ifstream infile(filename);
    if(infile.fail()){
        cout << "file not exists!" << endl;
        return 1;
    } else {
    	infile.close();
    }

	cout << "List of angler who has caught only and at least three catfishes on one of his contests:" << endl;
	ContestEnumerator enor(filename);
	ContestCounting pr;
	pr.addEnumerator(&enor);
	pr.run();
	cout << endl;
	cout << "The number of contests where at least one angler has caught only and at least three catfishes: ";
	cout << pr.result() << endl;
    return 0;
}

//type of results
struct Result{
    string contestID;
    bool hasOnlyAtLeastThreeCatfish;
    Result(){}
    Result(string a, bool b) : contestID(a), hasOnlyAtLeastThreeCatfish(b) {}
};

//class of a search Contest which has at least one angler has caught only and at least three catfishes into two summation
//overrides the method func(), neutral(), add(), first()
class ContestSummation : public Summation<Contest, Result>
{
private:
    string contestID;
public:
    ContestSummation(const string& str) : contestID(str) {}
protected:
    Result func(const Contest& e) const override {
        return Result( e.contestID, e.hasOnlyAtLeastThreeCatfish);
    }
    Result neutral() const override {
        return Result( "", false);
    }
    Result add(const Result &a, const Result &b) const override {
        return Result(b.contestID, a.hasOnlyAtLeastThreeCatfish || b.hasOnlyAtLeastThreeCatfish);
    }
    void first() override {}
    bool whileCond(const Contest& e) const override {
        return e.contestID.compare(contestID) == 0;
    }
};

//type of results
struct AnglerFishResult{
    int numCatfish;
    bool isOnlyCatfish;
    AnglerFishResult(){}
    AnglerFishResult(int a, bool b) : numCatfish(a), isOnlyCatfish(b) {}
};

//class of a conditional maximum search and an optimist linear search embedded into two summation
//overrides the method func(), neutral(), add(), first()
class AnglerFishSummation : public Summation<Fish, AnglerFishResult>
{
public:
	AnglerFishSummation() {}
protected:
	AnglerFishResult func(const Fish& e) const override {
		if(e.species.compare("catfish") == 0){
			return AnglerFishResult( 1, true );
		} else if(e.species.compare("") == 0){
			return AnglerFishResult( 0, true );
		} else {
			return AnglerFishResult( 0, false );
		}
    }
	AnglerFishResult neutral() const override {
        return AnglerFishResult(0, true);
    }
	AnglerFishResult add(const AnglerFishResult &a, const AnglerFishResult &b) const override {
        return AnglerFishResult(a.numCatfish + b.numCatfish, a.isOnlyCatfish && b.isOnlyCatfish);
    }
    void first() override {}
    bool whileCond(const Fish& e) const override {
        return true;
    }
};

//Task: 	reading the next contest
//Input:    SeqInFileEnumerator<Fish> *_f    - sequential input file of contests
//Output:   Contest _current                       - the current contest
//          bool _end                               - flag of the end of contests
//Activity: reading the next contest with processed data
void ContestEnumerator::next(){
    if((_end = _f->end())) return;

    _current.contestID = _f->current().contestID;
    ContestSummation pr(_current.contestID);
    pr.addEnumerator(_f);
    pr.run();
    _current.contestID = pr.result().contestID;
    _current.hasOnlyAtLeastThreeCatfish = pr.result().hasOnlyAtLeastThreeCatfish;
}

istream& operator>>(istream& in, Fish &e)
{
	in >> e.species >> e.size;
	return in;
}

//reading of Contest in every line
istream& operator>>(istream& in, Contest &e)
{
    string line;
    in >> e.anglerName >> e.contestID;
    getline(in, line);
    stringstream is(line);

    StringStreamEnumerator<Fish> enor(is);
    AnglerFishSummation pr;
    pr.addEnumerator(&enor);
    pr.run();

    int numCatfish = pr.result().numCatfish;
    bool onlyCatfish = pr.result().isOnlyCatfish;
    e.hasOnlyAtLeastThreeCatfish = numCatfish >= 3 && onlyCatfish;
    if(e.hasOnlyAtLeastThreeCatfish){
    	cout << e.anglerName << ", " << e.contestID << endl;
    }
    return in;
}

