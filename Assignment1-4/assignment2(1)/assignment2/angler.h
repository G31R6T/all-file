//Author:   Gregorics Tibor
//Date:     2018.09.23.
//Title:    Anglers

#pragma once

#include <fstream>
#include <sstream>
#include <string>

//Datatype of anglers
struct Angler {
    std::string angler;
    std::string contest;
    int counter;
    bool onlyCatfish;
};

//Datatype of the enumerator of anglers
//This is the enumerator for the first part
class AnglerEnor{
    private:
        std::ifstream _f;
        Angler _cur;
        bool _end;
    public:
        enum FileError{MissingInputFile};
        AnglerEnor(const std::string &str) throw (FileError);
        void first() { next(); }
        void next();
        Angler current() const { return _cur; }
        bool end() const { return _end; }
};
