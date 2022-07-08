//Author:   Gregorics Tibor
//Date:     2018.09.23.
//Title:    Anglers

#include "contest.h"

using namespace std;

//next() method for the second task
//Task: 	Reading the next contest, indicates whether has at least one angler has caught only and at least three catfishes
//Input:    ContestEnor tt  - the enumerator of contests
//Output:   Contest _cur     - the next contest
//          bool _end       - the end of the enumeration
//Activity:
void ContestEnor::next()
{
    if( !(_end = _tt.end()) ){
        _cur.contestID = _tt.current().contest;
        _cur.hasOnlyLeastThreeCatfishes = false;
        for( ; !_tt.end() && _tt.current().contest == _cur.contestID; _tt.next() ){
            if(_tt.current().counter >= 3 &&_tt.current().onlyCatfish){
                _cur.hasOnlyLeastThreeCatfishes = true;
            }
        }
    }
}
