
//Author:   lizhipeng
//Date:     2021.04.7.
//Title:    Anglers

#ifndef QUESTION5A_H
#define QUESTION5A_H

#include<string>
#include<fstream>
#include<sstream>
//Datatype of Contest
struct Contest{
    std::string anglerName;
    std::string contestID;
    int catfishCounter;

};
//Datatype of the enumerator of contests
//This is the enumerator for the first part
class question5a
{
public:
    enum Error{InvalidFile};
    question5a(const std::string &file);
    void first(){next();};
    void next();
    Contest current()const{return curr;};
    bool end()const{return _end;};

private:
    std::ifstream infile;
    Contest curr;
    bool _end;

};

#endif // QUESTION5A_H
