//Author:    Gregorics Tibor
//Date:      2017.08.08.
//Title:     Block matrix

#pragma once
#include <iostream>
#include <vector>

//Class of Block matrices
//Methods: add, multiply, write, read, refer to an element
//Representation: only the elements of the Block
class Block {
public:
    enum Exceptions{OVERINDEXED, NULLPART, DIFFERENT, INVALID};

    Block(){ _b1v.clear(); _b2v.clear(); }
    Block(int b1, int b2);
    ~Block() { }
    Block(const std::vector<std::vector<int>> &b1v, const std::vector<std::vector<int>> &b2v);
    Block(const Block& a);
    Block& operator=(const Block& a);
    int getBlock1Size() const {return _b1v.size();}
    int getBlock2Size() const {return _b2v.size();}

    int operator()(int i, int j) const;
    int& operator()(int i, int j);

    friend Block operator+ (const Block& a, const Block& b);
    friend Block operator* (const Block& a, const Block& b);
    friend std::istream& operator>> (std::istream& s, Block& a);
    friend std::ostream& operator<< (std::ostream& s, const Block& a);
private:
    std::vector<std::vector<int>> _b1v;
    std::vector<std::vector<int>> _b2v;

    void reSize(int b1, int b2);
};
