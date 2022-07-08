#include <iostream>
#include "question5a.h" //this is not necessary when we use the next include (only for the first task)
#include "Angler.h"

using namespace std;

//First task
//Activity: Looking for the first angler, who has caught at least two catfishes on a contest
bool first_search(const string &name, Contest& elem)
{
    ContestEnor t(name);
    bool l = false;
    for(t.first(); !l && !t.end(); t.next()){
        l = t.current().counter >= 3;
        elem = t.current();
    }
    return l;
}

//Second task
//Activity:Counting everyContests which looking for a skillful angler who has caught at least 3 catfishes on each his contests
bool second_search(const string &name, string &id)
{
    Angler elem;
    AnglerEnor t(name);
    bool l = false;
    for(t.first(); !l && !t.end(); t.next()){
        l = t.current().skillful;
        elem = t.current();
    }
    if (l)
        id = elem.id;
    return l;
}

#define NORMAL_MODE
#ifdef NORMAL_MODE2

//Activity:
//First: Looking for the first angler, who has caught at least two catfishes on a contest
//Second: Looking for a skillful angler who has caught at least two catfishes on each his contests
int main()
{

    string filename;
    cout<<"Enter the name of the input file, please: "; cin>>filename;

    //First task
    cout<<"First  task\n";
    try{
        Contest e;
        if(first_search(filename, e)){
            cout<<e.angler<<" has cought "<<e.counter<<" catfish(es) on contest "<<e.contest<<endl;
        }else{
            cout<<"There is no angler matching our search criteria.\n";
        }
    }catch(ContestEnor::FileError err)
    {
        cerr<<"Can't find input file "<<filename<<"!"<<endl;
    }

    //Second task
    cout<<"Second task\n";

    try{
        string id;
        if(second_search(filename, id)) cout << id << " is a";
        else cout << "There is no";
        cout << " skillful angler.\n";
    }catch(ContestEnor::FileError err)
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
    Contest e;
    CHECK_FALSE(first_search("t0.txt", e));
}

TEST_CASE("first task 1 angler, true", "t2.txt") {
    Contest e;
    CHECK(first_search("t2.txt", e));
    CHECK(e.angler=="Peter");
}

TEST_CASE("first task 1 angler, false", "t13.txt") {
    Contest e;
    CHECK_FALSE(first_search("t13.txt", e));
}

TEST_CASE("first task more anglers, first one meets the requirements", "tt3.txt") {
    Contest e;
    CHECK(first_search("tt3.txt", e));
    CHECK((e.angler=="Peter" && e.contest=="Kiliti0512")==TRUE);
}

TEST_CASE("first task more anglers, third one meets the requirements", "tt5.txt") {
    Contest e;
    CHECK(first_search("tt5.txt", e));
    CHECK(e.angler=="Nick");
}

TEST_CASE("first task more anglers, last one meets the requirements", "tt6.txt") {
    Contest e;
    CHECK(first_search("tt6.txt", e));
    CHECK(e.angler=="John");
}

TEST_CASE("first task more anglers, no one meets the requirements", "t6.txt") {
    Contest e;
    CHECK_FALSE(first_search("t6.txt", e));
}

// counting

TEST_CASE("no catch", "t8.txt") {
    ContestEnor tt("t8.txt");
    tt.first();
    CHECK(tt.current().counter == 0);
}

TEST_CASE("1 catch", "t3.txt") {
    ContestEnor tt("t3.txt");
    tt.first();
    CHECK(tt.current().counter == 1);
}

TEST_CASE("more catches", "t2.txt") {
    ContestEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

TEST_CASE("first catch is catfish", "t2.txt") {
    ContestEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

TEST_CASE("last catch is catfish", "t2.txt") {
    ContestEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

TEST_CASE("no catfish", "t13.txt") {
    ContestEnor tt("t13.txt");
    tt.first();
    CHECK(tt.current().counter == 0);
}

TEST_CASE("1 catfish", "t12.txt") {
    ContestEnor tt("t12.txt");
    tt.first();
    CHECK(tt.current().counter == 1);
}

TEST_CASE("more catfishes", "t2.txt") {
    ContestEnor tt("t2.txt");
    tt.first();
    CHECK(tt.current().counter == 2);
}

// linear search second part

TEST_CASE("empty file", "t0.txt") {
    string id;
    CHECK_FALSE(second_search("t0.txt", id));
}

TEST_CASE("1 angler", "t2.txt") {
    string id;
    CHECK(second_search("t2.txt", id));
    CHECK(id=="Peter");
}

TEST_CASE("more angler more contests", "t4.txt") {
    string id;
    CHECK(second_search("t4.txt", id));
}

TEST_CASE("first angler is skillful", "t4.txt") {
    string id;
    CHECK(second_search("t4.txt", id));
    CHECK(id=="Peter");
}

TEST_CASE("last angler is skillful", "t5.txt") {
    string id;
    CHECK(second_search("t5.txt", id));
    CHECK(id=="Bob");
}

TEST_CASE("one skillful among more anglers more contests ", "t4.txt") {
    string id;
    CHECK(second_search("t4.txt", id));
}

TEST_CASE("no skillful anglers more contests ", "t6.txt") {
    string id;
    CHECK_FALSE(second_search("t6.txt", id));
}

TEST_CASE("more skillful anglers more contests ", "t14.txt") {
    string id;
    CHECK(second_search("t14.txt", id));
    CHECK(id=="John");
}

// optimistic linear search

TEST_CASE("no contest", "t1.txt") {
    AnglerEnor t("t1.txt");
    t.first();
    CHECK(t.current().skillful);
}

TEST_CASE("1 angler 1 contest", "t2.txt") {
    AnglerEnor t("t2.txt");
    t.first();
    CHECK(t.current().skillful);
}

TEST_CASE("1 angler more contest", "t3.txt") {
    AnglerEnor t("t3.txt");
    t.first();
    CHECK_FALSE(t.current().skillful);
}

TEST_CASE("1 angler on his first contest did not catch 2 catfish", "t3.txt") {
    AnglerEnor t("t3.txt");
    t.first();
    CHECK_FALSE(t.current().skillful);
}

TEST_CASE("1 angler on his last contest did not catch 2 catfish", "t9.txt") {
    AnglerEnor t("t9.txt");
    t.first();
    CHECK_FALSE(t.current().skillful);
}

TEST_CASE("there is a skillful angler", "t11.txt") {
    AnglerEnor t("t11.txt");
    t.first();
    CHECK(t.current().skillful);
}

TEST_CASE("no skillful angler", "t10.txt") {
    AnglerEnor t("t10.txt");
    t.first();
    CHECK_FALSE(t.current().skillful);
}

TEST_CASE("there are more skillful anglers", "t7.txt") {
    AnglerEnor t("t7.txt");
    t.first();
    CHECK(t.current().skillful);
}


#endif