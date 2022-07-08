//Author:    Gregorics Tibor
//Date:      2017.08.08.
//Title:     Block matrix

#include <iostream>
#include "block.h"

using namespace std;

#define NORMAL_MODE
#ifdef NORMAL_MODE

//class of menu for Block matrix
class Menu
{
public:
    Menu() : a(0, 0) { }
    void run();
private:
    Block a;

    void menuWrite();
    void get() const;
    void set();
    void read();
    void write();
    void sum();
    void mul();
};

int main()
{
//    setlocale(LC_ALL,"Hun");
    Menu m;
    m.run();
}

void Menu::run()
{
    int n = 0;
    do
    {
        menuWrite();
        cout << endl << ">>>>" ;
        cin >> n;
        switch(n)
        {
        case 1:
            get();
            break;
        case 2:
            set();
            break;
        case 3:
            read();
            break;
        case 4:
            write();
            break;
        case 5:
            sum();
            break;
        case 6:
            mul();
            break;
        }
    }
    while(n!=0);
}

void Menu::menuWrite()
{
    cout << endl << endl;
    cout << " 0. - Quit" << endl;
    cout << " 1. - Get an element of the matrix" << endl;
    cout << " 2. - Overwrite an element of the matrix" << endl;
    cout << " 3. - Read matrix" << endl;
    cout << " 4. - Write matrix" << endl;
    cout << " 5. - Add matrices" << endl;
    cout << " 6. - Multiply matrices" << endl;
}

void Menu::get() const
{
    int i,j;
    cout << "Give the index of the row: ";
    cin >> i;
    cout << "Give the index of the column: ";
    cin >> j;
    try
    {
        cout << "a[" << i << "," << j << "]= " << a(i-1,j-1) << endl;
    }
    catch(Block::Exceptions ex)
    {
        if(ex == Block::OVERINDEXED)
            cout << "Overindexing!" << endl;
        else
            cout << "Unhandled ecxeption!" << endl;
    }
}

void Menu::set()
{
    int i,j,e;
    cout << "Give the index of the row: ";
    cin >> i;
    cout << "Give the index of the column: ";
    cin >> j;
    cout << "Give the value: ";
    cin >> e;
    try
    {
        a(i-1,j-1) = e;
    }
    catch(Block::Exceptions ex)
    {
        if(ex == Block::OVERINDEXED)
            cout << "Overindexing!" << endl;
        if (ex == Block::NULLPART)
            cout << "These indexes do not point to the Block!" << endl;
    }
}

void Menu::read()
{
    try
    {
        cout << "Give the size and the items of the matrix: ";
        cin >> a;
    }
    catch(Block::Exceptions ex)
    {
        if(ex == Block::INVALID)
            cout << "Invalid size!" << endl;
        else
            cout << "Unhandled ecxeption!" << endl;
    }
}

void Menu::write()
{
    cout << a << endl;
}

void Menu::sum()
{
    try
    {
        Block b;

        cout << "Give the size and the items of the second  matrix: " << endl;
        cin >> b;
        cout << "Sum of the matrices: " << endl;
        cout << a + b << endl;
    }
    catch(Block::Exceptions ex)
    {
        if(ex == Block::INVALID)
            cout << "Invalid size!" << endl;
        if(ex == Block::DIFFERENT)
            cout << "Different sizes!" << endl;
    }
}

void Menu::mul()
{
    try
    {
        Block b;

        cout << "Give the size and the items of the second  matrix: " << endl;
        cin >> b;
        cout << "Product of the matrices: " << endl;
        cout << a * b << endl;
    }
    catch(Block::Exceptions ex)
    {
        if(ex == Block::INVALID)
            cout << "Invalid size!" << endl;
        if(ex == Block::DIFFERENT)
            cout << "Different sizes!" << endl;
    }
}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

TEST_CASE("create", "inp.txt")
{
    const string fileName = "inp.txt";

    ifstream in(fileName.c_str());
    if(in.fail())
    {
        cout << "File name error!" << endl;
        exit(1);
    }

    Block c;
    in >> c; // b1, b2 = 4, 5
    CHECK(c(0,0) == 1);
    CHECK(c(3,0) == 1);
    CHECK(c(0,3) == 4);
    CHECK(c(3,3) == 4);
    CHECK(c(8,8) == 5);

    Block b;
    in >> b; // b1, b2 = 3, 2
    CHECK(b(0,0) == 1);
    CHECK(b(2,0) == 1);
    CHECK(b(4,4) == 2);

    Block a;
    in >> a; // b1, b2 = 2, 1
    CHECK(a(0, 0)==1);
    CHECK(a(0, 1)==2);
    CHECK(a(2,2) == 1);
}

TEST_CASE("getting and changing an element of the matrix", "")
{
    Block a( {{1,2}, {1,2}}, {{1}} );
    CHECK(a(0,0) == 1);
    a(0,0) = 0;
    CHECK(a(0,0) == 0);
}

TEST_CASE("copy constructor", "inp.txt")
{
    const string fileName = "inp.txt";

    ifstream in(fileName.c_str());
    if(in.fail())
    {
        cout << "File name error!" << endl;
        exit(1);
    }

    Block a;
    in >> a; // b1, b2 = 4 5

    Block b = a;

    CHECK(a(0,0) == b(0,0));
    CHECK(a(1,1) == b(1,1));
    CHECK(a(2,2) == b(2,2));
}

TEST_CASE("assignment operator", "inp.txt")
{
    const string fileName = "inp.txt";

    ifstream in(fileName.c_str());
    if(in.fail())
    {
        cout << "File name error!" << endl;
        exit(1);
    }

    Block a, b; // b1, b2 = 4, 5
    in >> a;

    b = a; // check every non-zero item
    CHECK(a(0,0) == b(0,0));
    CHECK(a(1,1) == b(1,1));
    CHECK(a(2,2) == b(2,2));

    Block c; // 3
    c = b = a; // check every non-zero item
    CHECK(a(0,0) == c(0,0));
    CHECK(a(1,1) == c(1,1));
    CHECK(a(2,2) == c(2,2));

    a = a; // check every non-zero item
    CHECK(a(0,0) == 1);
    CHECK(a(3,0) == 1);
    CHECK(a(0,3) == 4);
    CHECK(a(3,3) == 4);
    CHECK(a(8,8) == 5);
}

TEST_CASE("add", "inp2.txt")
{
    const string fileName = "inp2.txt";

    ifstream in(fileName.c_str());
    if(in.fail())
    {
        cout << "File name error!" << endl;
        exit(1);
    }

    Block a, b, c, d, f, e, z; // b1, b2 = 4, 5
    in >> a >> b >> z >> e;

    c = a + b; // check every non-zero item
    CHECK(c(0,0) == 4);
    CHECK(c(0,3) == 7);
    CHECK(c(8,8) == 8);

    d = b + a; // check every non-zero item
    CHECK(c(0,0) == d(0,0));
    CHECK(c(0,3) == d(0,3));
    CHECK(c(8,8) == d(8,8));

    d = (a + b) + c;
    f = a + (b + c); // check every non-zero item
    CHECK(d(0,0) == f(0,0));
    CHECK(d(0,3) == f(0,3));
    CHECK(d(8,8) == f(8,8));

    c = a + z; // check every non-zero item
    CHECK(c(0,0) == a(0,0));
    CHECK(c(0,3) == a(0,3));
    CHECK(c(8,8) == a(8,8));
}

TEST_CASE("multiply", "inp2.txt")
{
    const string fileName = "inp2.txt";

    ifstream in(fileName.c_str());
    if(in.fail())
    {
        cout << "File name error!" << endl;
        exit(1);
    }

    Block a, b, c, d, f, e, z; // b1, b2 = 4, 5
    in >> a >> b >> z >> e;

    c = a * b;
    CHECK(c(0,0) == 12);
    CHECK(c(0,3) == 48);
    CHECK(c(4,4) == 15);
    CHECK(c(8,8) == 75);

    d = b * a;
    CHECK(d(0,0) == 30);
    CHECK(d(0,3) == 30);
    CHECK(d(4,4) == 45);
    CHECK(d(8,8) == 45);

    d = (a * b) * c;
    f = a * (b * c);
    CHECK(d(0,0) == f(0,0));
    CHECK(d(0,3) == f(0,3));
    CHECK(d(4,4) == f(4,4));
    CHECK(d(8,8) == f(8,8));

    c = a * e;
    CHECK(c(0,0) == 12);
    CHECK(c(0,3) == 12);
    CHECK(c(4,4) == 15);
    CHECK(c(8,8) == 15);
}

TEST_CASE("exceptions", "")
{

    Block a(4, 5);

    try
    {
        a(9,9) = 4;
    }
    catch(Block::Exceptions ex)
    {
        CHECK(ex == Block::OVERINDEXED);
    }

    try
    {
        a(-1,4) = 4;
    }
    catch(Block::Exceptions ex)
    {
        CHECK(ex == Block::OVERINDEXED);
    }

    Block b(2, 3);
    Block c(3, 4);

    try
    {
        a = b;
    }
    catch(Block::Exceptions ex)
    {
        CHECK(ex == Block::DIFFERENT);
    }

    try
    {
        c = a + b;
    }
    catch(Block::Exceptions ex)
    {
        CHECK(ex == Block::DIFFERENT);
    }

    try
    {
        c = a * b;
    }
    catch(Block::Exceptions ex)
    {
        CHECK(ex == Block::DIFFERENT);
    }


    try
    {
        a(4,4) = 4;
    }
    catch(Block::Exceptions ex)
    {
        CHECK(ex == Block::NULLPART);
    }

    try
    {
        int k = a(4, 4);
    }
    catch(Block::Exceptions ex)
    {
        CHECK(ex == Block::NULLPART);
    }
}

#endif
