
#include "question5a.h"

using namespace std;

//next() method for the second task
//Task: 	Reading the next angler with his ability
//Input:    ContestEnor tt  - the enumerator of contests
//Output:   Angler _cur     - the next angler
//          bool _end       - the end of the enumeration
//Activity:


void question5A(const string &file){
    question5a tt(file);
    Contest cc;
    for(tt.first();!tt.end();tt.next()){
        if(tt.current().catfishCounter>=3){
            cc.anglerName = tt.current().anglerName;
            cc.contestID = tt.current().contestID;
            break;
        }
    }
    cout<<cc.anglerName<<" with ContestID "<<cc.contestID<<" has Caught at least three catfish"<<endl;

}