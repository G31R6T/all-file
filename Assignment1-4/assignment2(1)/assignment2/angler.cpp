//Author:   Gregorics Tibor
//Date:     2018.09.23.
//Title:    Anglers

#include "angler.h"

using namespace std;

//Input:    string str  - the test file's name
//Activity: tries to open the given file. If the opening fails, it throws a "FileError" exception.
AnglerEnor::AnglerEnor(const std::string &str) throw (FileError)
{
    _f.open(str);
    if(_f.fail()) throw MissingInputFile;
}

//Task: 	Reading the next row of the input file
//Input:    ifstream _f  - the test file
//Output:   Contest _cur - the next contest
//          bool _end    - the end of the enumeration
//Activity:
void AnglerEnor::next()
{
    string line;
    getline(_f , line, '\n');
    if( !(_end = _f.fail()) ){
        istringstream is(line);
        is >> _cur.angler >> _cur.contest;
        _cur.counter = 0;
        _cur.onlyCatfish = true;
        string fish;
        int size;
        for( is >> fish >> size ; !is.fail(); is >> fish >> size ){
            if(fish == "catfish") {
            	 ++_cur.counter;
            } else {
            	_cur.onlyCatfish = false;
            }
        }
    }
}
