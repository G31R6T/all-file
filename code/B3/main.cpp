#include <iostream>

using namespace std;

int tree [100];
int K;
int trees(int price )
{
    int t=0;
    while(tree[price]<10000 && tree[price]>K && tree[price]>tree[price+1] )
        t++;
    if(t > K )


        return -1;
}

int main()
{
    int N;
cout<<"enter the number of tree:";
cin>>N;
cout<<"the price is :";
cin>>K;

int trees[N];
int index;
for(index=0;index<N;index++){
        cout<<"enter the price:";
        cin>>trees[index];

        return 0;
}}
