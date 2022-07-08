#include <iostream>
#include <vector>
using namespace std;


int main()
{
	int K;
	int T;
	int i;
	int differentProfessions = 0;
	cin >> K;
	cin >> T;
	string professions[T];
	for(i = 0;i < T;i++) {
		cin >> professions[i];
	}
	cout << "#" << endl;
	cout << differentProfessions << endl;
	cout << "#" << endl;

	return 0;
}

