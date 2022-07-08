#include <iostream>
#include <vector>
using namespace std;



int getExistProfessionIndex(char *profession, char professions[][20], int n) {
	for (int i = 0; i < n; i++) {
		if (isSameString(profession, professions[i])) {
			return i;
		}
	}
	return -1;
}

int main() {
	int K;
	int T;
	int i;
	int differentProfessions = 0;
	cin >> K;
	cin >> T;
	int schoolID;
	char professions[T][20];
	int professionIndex;
	vector<int> professionSchools[T];
	for (i = 0; i < T; i++) {
		cin >> professions[differentProfessions];
		cin >> schoolID;
		professionIndex = getExistProfessionIndex(professions[differentProfessions], professions, differentProfessions);
		if (professionIndex < 0) {
			differentProfessions++;
			professionIndex = differentProfessions - 1;
		}
		professionSchools[professionIndex].push_back(schoolID);
	}
	cout << "#" << endl;
	cout << differentProfessions << endl;
	cout << "#" << endl;

	return 0;
}

