//Author:    Gregorics Tibor
//Date:      2017.08.08.
//Title:     Block matrix

#include "block.h"
#include <iostream>
#include <iomanip>

using namespace std;

//Task: 	constructor with size
//Input:    int b1     - the block 1 size
//          int b2     - the block 2 size
//Output:   Block this - default matrix
//Activity: creates the array of the Block of size b1 and b2
Block::Block(int b1, int b2)
{
	int i;
    if (b1 < 0 || b2 < 0) throw INVALID;
    _b1v.clear();
    _b2v.clear();
    this->reSize(b1, b2);
}

//Task: 	constructor the elements of the Block
//Input:    vector<vector<int>> b1v     - the new block 1
//          vector<vector<int>> b2v     - the new block 2
//Output:   Block this         - default matrix
//Activity: creates the array of the Block and fills in its elements based on vector v
Block::Block(const std::vector<std::vector<int>> &b1v, const std::vector<std::vector<int>> &b2v)
{
	_b1v = b1v;
	_b2v = b2v;
}

//Task: 	copy constructor
//Input:    Block a    - matrix
//Output:   Block this - default matrix
//Activity: creates the array of the Block and fills in its elements based on matrix a
Block::Block(const Block& a)
{
    this->reSize(a.getBlock1Size(), a.getBlock2Size());
    for (int i = 0; i < a.getBlock1Size(); i++) { //copy block 1
		for (int j = 0; j < _b1v[i].size(); j++) {
			_b1v[i][j] = a._b1v[i][j];
		}
	}
	for (int i = 0; i < a.getBlock2Size(); i++) { //copy block 2
		for (int j = 0; j < _b2v[i].size(); j++) {
			_b2v[i][j] = a._b2v[i][j];
		}
	}
}

//Task: 	assignment operator
//Input:    Block a    - matrix
//Output:   Block this - default matrix
//Activity: fills in the elements of the Block based on matrix a
Block& Block::operator=(const Block& a)
{
	this->reSize(a.getBlock1Size(), a.getBlock2Size());
    for (int i = 0; i < a.getBlock1Size(); i++) { //copy block 1
		for (int j = 0; j < _b1v[i].size(); j++) {
			_b1v[i][j] = a._b1v[i][j];
		}
	}
	for (int i = 0; i < a.getBlock2Size(); i++) { //copy block 2
		for (int j = 0; j < _b2v[i].size(); j++) {
			_b2v[i][j] = a._b2v[i][j];
		}
	}
    return *this;
}

//Task: 	resizing a matrix
//Input:    int b1     - the block 1 size
//          int b2     - the block 2 size
//Output:   Block this - default matrix
//Activity: resizes the matrix to b1 and b2
void Block::reSize(int b1, int b2)
{
	int i;
    if(b1 < 0 || b2 < 0) throw INVALID;
    _b1v.resize(b1, std::vector<int>());
	_b2v.resize(b2, std::vector<int>());
	for (i = 0; i < b1; i++) { //resize block 1
		_b1v[i].resize(b1, 0);
	}
	for (i = 0; i < b2; i++) { //resize block 2
		_b2v[i].resize(b2, 0);
	}
}

//Task: 	adding
//Input:    Block a    - matrix
//          Block b    - matrix
//Output:   Block      - result matrix
//Activity: adds the elements of the Blocks of the matrices
Block operator+(const Block& a ,const Block& b)
{
    if(a.getBlock1Size() != b.getBlock1Size() || a.getBlock2Size() != b.getBlock2Size()) {
    	throw Block::DIFFERENT;
    }

    Block c(a.getBlock1Size(), a.getBlock2Size());

    for(int i = 0; i < a.getBlock1Size(); ++i) { // add block 1
    	for(int j = 0;j < a._b1v.size();j++){
    		c._b1v[i][j] = a._b1v[i][j] + b._b1v[i][j];
    	}
    }
    for(int i = 0; i < a.getBlock2Size(); ++i) { // add block 2
    	for(int j = 0;j < a._b2v.size();j++){
    		c._b2v[i][j] = a._b2v[i][j] + b._b2v[i][j];
    	}
    }
    return c;
}

//Task: 	multiplying
//Input:    Block a    - matrix
//          Block b    - matrix
//Output:   Block      - result matrix
//Activity: multiplies the elements of the Blocks of the matrices
Block operator*(const Block& a ,const Block& b)
{
	int b1 = a.getBlock1Size();
	int b2 = a.getBlock2Size();
    if(a.getBlock1Size() != b.getBlock1Size() || a.getBlock2Size() != b.getBlock2Size()) {
    	throw Block::DIFFERENT;
    }

    Block c(b1, b2);

    for(int i = 0;i < b1;i++){ // multiply block 1
    	for(int j = 0;j < b1;j++){
    		for(int k = 0;k < b1;k++){
    			c._b1v[i][j] += a._b1v[i][k] * b._b1v[k][j];
    		}
    	}
    }
    for(int i = 0;i < b2;i++){ // multiply block 2
    	for(int j = 0;j < b2;j++){
    		for(int k = 0;k < b2;k++){
    			c._b2v[i][j] += a._b2v[i][k] * b._b2v[k][j];
    		}
    	}
    }
    return c;
}

//Task: 	writing
//Input:    ostream os - target of writing
//          Block a     - matrix
//Output:   ostream os - target of writing
//Activity: writes the elements of the matrix
ostream& operator<<(ostream& os, const Block& a)
{
    int s = a.getBlock1Size() + a.getBlock2Size();
    for(int i = 0; i < s; ++i){
        for(int j = 0; j < s; ++j)
            os << setw(5) << a(i,j);
        os << endl;
    }
    return os;
}

//Task: 	reading
//Input:    istream is - source of writing
//          Block a     - matrix
//Output:   istream is - source of writing
//Activity: reads the elements of the Block of the matrix
istream& operator>>(istream& is, Block& a)
{
    int b1, b2;
    is >> b1 >> b2; // read block 1 and block 2's size
    a.reSize(b1, b2);
    for(int i = 0; i < b1; i++) { // read data in block 1
        for(int j = 0;j < b1;j++){
        	is >> a(i, j);
        }
    }
    for(int i = 0; i < b2; i++) { // read data in block 2
        for(int j = 0;j < b2;j++){
        	is >> a(b1 + i, b1 + j);
        }
    }
    return is;
}

//Task: 	getting
//Input:    int i,j - indexes of the element
//Output:   int     - the element of the matrix in the ith row and jth column
//Activity: gets the given element of the Block matrix
int Block::operator()(int i, int j) const
{
    int b1 = this->getBlock1Size();
    int b2 = this->getBlock2Size();
    int s = b1 + b2;
    if ((i >= s || i < 0 ) || (j >= s || j < 0 )) throw OVERINDEXED; // throw exception if (i, j) out of range
    if (i < b1 && j < b1){ // (i, j) is in block 1
    	return _b1v[i][j];
    } else if(i >= b1 && j >= b1){ // (i, j) is in block 2
    	return _b2v[i-b1][j-b1];
    } else {
    	return 0; // (i, j) is not in block 1, 2
    }
}

//Task: 	setting
//Input:    int i,j - indexes of the element
//Output:   int     - the element of the matrix in ith row and jth column
//Activity: gives a reference to the given elements of the Block matrix
int& Block::operator()(int i, int j)
{
    int b1 = this->getBlock1Size();
    int b2 = this->getBlock2Size();
    int s = b1 + b2;
    if ((i >= s || i < 0 ) || (j >= s || j < 0 )) throw OVERINDEXED; // throw exception if (i, j) out of range
    if (i < b1 && j < b1){ // (i, j) is in block 1
    	return _b1v[i][j];
    } else if(i >= b1 && j >= b1){ // (i, j) is in block 2
    	return _b2v[i-b1][j-b1];
    } else {
    	throw NULLPART; // (i, j) is not in block 1, 2
    }
}

