#include<iostream>
using namespace std;

int main()
{
    string names[4]={"Asdc","Basd","Cdfg","Dhrthrth"};
    int index;
    int longs=0;

    for(index=1;index<4;index++);
    if(names[index].length()>names[longs].length())
        longs = index;

    cout<<"the"<<names[longs]<<endl;
    return 0;

}
