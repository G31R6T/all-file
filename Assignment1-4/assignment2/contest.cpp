#include "question5a.h"
//Input:    string str  - the test file's name
//Activity: tries to open the given file. If the opening fails, it throws a "FileError" exception.
question5a::question5a(const std::string &str)
{
    infile.open(str);
    if(infile.fail()) throw Error::InvalidFile;
}
//Task: 	Reading the next row of the input file
//Input:    ifstream _f  - the test file
//Output:   Contest _cur - the next contest
//          bool _end    - the end of the enumeration
//Activity:
void question5a::next(){
    std::string line;
    std::getline(infile,line,'\n');
    if(!(_end=infile.fail())){
        std::istringstream is(line);
        is>>curr.anglerName>>curr.contestID;
        curr.catfishCounter = 0;
        std::string fish;
        int size;
        for(is>>fish>>size;!is.fail();is>>fish>>size){
            if(fish=="catfish"){
                curr.catfishCounter++;
            }
        }
    }


}
