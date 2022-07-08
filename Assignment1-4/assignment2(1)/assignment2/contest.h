//Author:   Gregorics Tibor
//Date:     2018.09.23.
//Title:    Anglers

#pragma once

#include "angler.h"
#include <string>


//Datatype of contests
struct Contest {
    std::string contestID;
    bool hasOnlyLeastThreeCatfishes;
};


//Datatype of the enumerator of contests for the second part
class ContestEnor{
    private:
        AnglerEnor _tt;
        Contest _cur;
        bool _end;
    public:
        ContestEnor(const std::string &str): _tt(str) { }
        void first() { _tt.first(); next(); }
        void next();
        Contest current() const { return _cur; }
        bool end() const { return _end; }
};

