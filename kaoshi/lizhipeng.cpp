#include <iostream>

using namespace std;
typedef struct{
    int id;
    string name;
    int year;
}things;
typedef struct{
    string s;
}names;
int main(){
    int num;
    int kind;
    cin>>num>>kind;

    things thing[num];
    for(int i=0;i<num;i++){
        cin>>thing[i].year>>thing[i].id>>thing[i].name;
    }
    names na[kind];
    int check[kind];
    for(int i=0;i<kind;i++){
        check[i]=0;
    }

    int fst;
    int lst;
    cin>>fst>>lst;

    for(int i=0;i<kind;i++){
        cin>>na[i].s;
    }
    for(int i=0;i<num;i++){
        check[thing[i].id-1]++;
    }
    int cnt=0;
    for(int i=0;i<kind;i++){
        if(check[i]==0){
            cnt++;
            //cout<<na[i].s;
        }
    }
    cout<<cnt<<" ";
    for(int i=0;i<kind;i++){
        if(check[i]==0){
            cout<<na[i].s<<" ";
        }
    }
    cout<<endl;

}




/*
4
4
862
1
j
1205
2
q
1453
3
k
1522
3
l
1000
1500
f
c
w
cc

typedef struct{
    int id;
    string name;
    int year;
}Set;
typedef struct{
     string kinds;
}Set2;
const int MaxN=100;
const int MaxM=100;
int main()
{
    int N;  cin>>N; int M; cin>>M;
    Set  S[MaxN];
    Set2 O[MaxM];
    for(int i=0;i<N;i++){
        cin>>S[i].year>>S[i].id>>S[i].name;
    }
    int year1; cin>>year1; int year2; cin>>year2;
    for(int i=0;i<M;i++){
        cin>>O[i].kinds;
    }
   int cnt=0;
    for(int i=0;i<N;i++){
        if(S[i].year<year1){
            cnt+=S[i].id;
        }
    }
    cout<<cnt<<endl;
    string cnt1;
    for(int j=0;j<M;j++){
        if(S[i].id==O[i].kinds){

           cnt1++;
        }
        else{
            O[j].kinds;
        }
    }
    cout<<cnt1<<endl;
    return 0;
}*/
