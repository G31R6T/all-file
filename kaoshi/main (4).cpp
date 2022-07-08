#include <iostream>

using namespace std;

int X[1000][7];

int increased(int week)
{
int r=0;
while(r<6 && X[week][r]>X[week][r+1])
     r++;
if(r==6)
    return 1;
else
    return 0;
}

int main()
{
int N;
do{
    cout << "Please enter the number of weeks: ";
    cin >> N;
  } while(N<2 || N>1000);


///input Rain data
for(int i=0;i<N;i++)
{
  for(int j=0;j<7;j++)
     do
       {
       cout << "Please enter the amount of rain on " << i+1 << " week " << j+1 << " day: ";
       cin >> X[i][j];
       }while(X[i][j]<0 || X[i][j]>1000);
}
///task a
int SumRain[N];

for(int i=0;i<N;i++)
{
    SumRain[i]=0;
    //cout << "The amount of rain on " << i << " week: "
    for(int j=0;j<7;j++)
        SumRain[i]=SumRain[i] + X[i][j];
    cout << SumRain[i] << " ";
}
cout << endl;

///task b
int MRI=0;
for(int k=1;k<N;k++)
    if(SumRain[k]>SumRain[MRI])
       MRI=k;
cout << MRI+1 << endl;

///task C
int M=0;
int IncWeek[N];
for(int l=0;l<N;l++)
    if(increased(l))
      {
      IncWeek[M]=l;
      M++;
      }
if(M>0)
{
    cout << M;
    for(int m=0;m<M;m++)
       cout << IncWeek[m] << " ";
}
cout << endl;
return 0;
}


