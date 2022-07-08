//Author:   Gregorics Tibor
//Date:     2018.09.23.
//Title:    Anglers
#include <iostream>
#include "contest.h" //this is not necessary when we use the next include (only for the first task)
#include "angler.h"
using namespace std;

//First task
//Activity: Looking for the first angler, who has caught only and at least three catfishes on a contest
bool first_search(const string &name, Angler& elem)
{
    AnglerEnor t(name);
    bool l = false;
    for(t.first(); !l && !t.end(); t.next()){
        l = t.current().counter >= 3 && t.current().onlyCatfish;
        elem = t.current();
    }
    return l;
}

//Second task
//Activity:  Looking for the number of contest which contains at least one angler has caught only and at least three catfishes
bool second_search(const string &name, int &contestNum)
{
    ContestEnor t(name);
    contestNum = 0;
    bool l = false;
    for(t.first(); !t.end(); t.next()){
        if(t.current().hasOnlyLeastThreeCatfishes){
            l = true;
            contestNum++;
        }
    }
    return l;
}

#define NORMAL_MODE
#ifdef NORMAL_MODE

//Activity:
//First: Looking for the first angler, who has caught only and at least three catfishes on a contest
//Second: Looking for the number of contests which contains at least one angler has caught only and at least three catfishes
int main()
{

    string filename;
    cout<<"Enter the name of the input file, please: "; cin>>filename;

    //First task
    cout<<"First  task\n";
    try{
        Angler e;
        if(first_search(filename, e)){
            cout<<e.angler<<" has caught only "<<e.counter<<" catfish(es) on contest "<<e.contest<<endl;
        }else{
            cout<<"There is no angler who has caught only and at least three catfishes on a contest.\n";
        }
    }catch(AnglerEnor::FileError err)
    {
        cerr<<"Can't find input file "<<filename<<"!"<<endl;
    }

    //Second task
    cout<<"Second task\n";

    try{
        int contestNum = 0;
        if(second_search(filename, contestNum)) {
            cout << contestNum << " contests contains at least one angler has caught only and at least three catfishes.\n";
        } else {
            cout << "There is no contests contains at least one angler has caught only and at least three catfishes.\n";
        }

    }catch(AnglerEnor::FileError err)
    {
        cerr<<"Can't find the input file:"<<filename<<endl;
    }
    return 0;
}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

//linear search first part

TEST_CASE("first task empty file", "t0.txt") {
    Angler e;
    CHECK_FALSE(first_search("t0.txt", e));
}

TEST_CASE("first task 1 angler, true", "t14.txt") {
    Angler e;
    CHECK(first_search("t14.txt", e));
    CHECK(e.angler=="Peter");
}

TEST_CASE("first task 1 angler, false", "t2.txt") {
    Angler e;
    CHECK_FALSE(first_search("t2.txt", e));
}

TEST_CASE("first task more anglers, first one meets the requirements", "tt3.txt") {
    Angler e;
    CHECK(first_search("tt3.txt", e));
    CHECK((e.angler=="Peter" && e.contest=="Kiliti0512")==TRUE);
}

TEST_CASE("first task more anglers, third one meets the requirements", "tt5.txt") {
    Angler e;
    CHECK(first_search("tt5.txt", e));
    CHECK((e.angler=="Peter" && e.contest=="Szeged0820")==TRUE);
}

TEST_CASE("first task more anglers, last one meets the requirements", "tt6.txt") {
    Angler e;
    CHECK(first_search("tt6.txt", e));
    CHECK(e.angler=="John");
}

TEST_CASE("first task more anglers, no one meets the requirements", "t6.txt") {
    Angler e;
    CHECK_FALSE(first_search("t6.txt", e));
}

//// counting

TEST_CASE("no catch", "t8.txt") {
    AnglerEnor tt("t8.txt");
    tt.first();
    CHECK(tt.current().counter == 0);
}

TEST_CASE("1 catch", "t3.txt") {
    AnglerEnor tt("t3.txt");
    tt.first();
    CHECK(tt.current().counter == 1);
}

TEST_CASE("more catches", "t2.txt") {
    AnglerEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

TEST_CASE("first catch is catfish", "t2.txt") {
    AnglerEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

TEST_CASE("last catch is catfish", "t2.txt") {
    AnglerEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

TEST_CASE("no catfish", "t13.txt") {
    AnglerEnor tt("t13.txt");
    tt.first();
    CHECK(tt.current().counter == 0);
}

TEST_CASE("1 catfish", "t12.txt") {
    AnglerEnor tt("t12.txt");
    tt.first();
    CHECK(tt.current().counter == 1);
}

TEST_CASE("more catfishes", "t2.txt") {
    AnglerEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

//// linear search second part

TEST_CASE("empty file", "t0.txt") {
    int contestNum;
    CHECK_FALSE(second_search("t0.txt", contestNum));
}

TEST_CASE("1 contest", "t14.txt") {
	int contestNum;
    CHECK(second_search("t14.txt", contestNum));
    CHECK(contestNum == 1);
}

TEST_CASE("more contests", "tt3.txt") {
	int contestNum;
    CHECK(second_search("tt3.txt", contestNum));
}

TEST_CASE("first contest contains angler who caught only and at least three catfishes", "t14.txt") {
	int contestNum;
    CHECK(second_search("t14.txt", contestNum));
    CHECK(contestNum == 1);
}

TEST_CASE("last contest contains angler who caught only and at least three catfishes", "t5.txt") {
	int contestNum;
    CHECK(second_search("t5.txt", contestNum));
    CHECK(contestNum == 1);
}

TEST_CASE("no contest contains angler who caught only and at least three catfishes", "t6.txt") {
	int contestNum;
    CHECK_FALSE(second_search("t6.txt", contestNum));
}

TEST_CASE("more contests contains angler who caught only and at least three catfishes ", "tt3.txt") {
	int contestNum;
    CHECK(second_search("tt3.txt", contestNum));
    CHECK(contestNum == 2);
}

//// optimistic linear search

TEST_CASE("no contest", "t1.txt") {
    ContestEnor t("t1.txt");
    t.first();
    CHECK_FALSE(t.current().hasOnlyLeastThreeCatfishes);
}

TEST_CASE("1 angler 1 contest", "t2.txt") {
    ContestEnor t("t2.txt");
    t.first();
    CHECK_FALSE(t.current().hasOnlyLeastThreeCatfishes);
}

TEST_CASE("1 angler more contest", "t3.txt") {
    ContestEnor t("t3.txt");
    t.first();
    CHECK_FALSE(t.current().hasOnlyLeastThreeCatfishes);
}

TEST_CASE("first contest did not contains angler who caught only and at least 3 catfish", "t3.txt") {
    ContestEnor t("t3.txt");
    t.first();
    CHECK_FALSE(t.current().hasOnlyLeastThreeCatfishes);
}

TEST_CASE("1 contest on last record did not contains angler who caught only and at least 3 catfish", "t9.txt") {
    ContestEnor t("t9.txt");
    t.first();
    CHECK_FALSE(t.current().hasOnlyLeastThreeCatfishes);
}

TEST_CASE("there is a contest which contains angler who caught only and at least 3 catfish", "t14.txt") {
    ContestEnor t("t14.txt");
    t.first();
    CHECK(t.current().hasOnlyLeastThreeCatfishes);
}

TEST_CASE("no contests which contains angler who caught only and at least three catfishes", "t10.txt") {
    ContestEnor t("t10.txt");
    t.first();
    CHECK_FALSE(t.current().hasOnlyLeastThreeCatfishes);
}

TEST_CASE("there are more contests which contains angler who caught only and at least three catfishes", "t7.txt") {
    ContestEnor t("t7.txt");
    t.first();
    CHECK(t.current().hasOnlyLeastThreeCatfishes);
}


#endif
