#include <iostream>
#include <vector>
using namespace std;

int isSameString(char *a, char *b) {
	int i = 0;
	int j = 0;
	while (a[i] == b[j] && a[i] != '\0' && b[j] != '\0') {
		i++;
		j++;
	}
	return i == j && a[i] == '\0' && b[j] == '\0';
}

int getExistProfessionIndex(char *profession, char professions[][20], int n) {
	for (int i = 0; i < n; i++) {
		if (isSameString(profession, professions[i])) {
			return i;
		}
	}
	return -1;
}

int isExist(vector<int> &a, int n) {
	vector<int>::iterator iter;
	for (iter = a.begin(); iter != a.end(); iter++) {
		if ((*iter) == n) {
			return 1;
		}
	}
	return 0;
}

int isSame(vector<int> &a, vector<int> &b) {
	vector<int>::iterator iter;
	if (a.size() != b.size()) {
		return 0;
	}
	for (iter = a.begin(); iter != a.end(); iter++) {
		if (!isExist(b, *iter)) {
			return 0;
		}
	}
	for (iter = b.begin(); iter != b.end(); iter++) {
		if (!isExist(a, *iter)) {
			return 0;
		}
	}
	return 1;
}

int getSchoolsAfterMerge(vector<int> *schoolProfessions, int K) {
	int i;
	int j;
	int numAfterMerge = K;
	for (i = 1; i <= K; i++) {
		if (schoolProfessions[i].size() == 0) {
			continue;
		}
		for (j = i + 1; j <= K; j++) {
			if (schoolProfessions[j].size() == 0) {
				continue;
			}
			if (isSame(schoolProfessions[i], schoolProfessions[j])) {
				numAfterMerge--;
				schoolProfessions[j].clear();
			}
		}
	}
	return numAfterMerge;
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
	int mostProfSchoolID;
	vector<int> schoolProfessions[K + 1];
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
		schoolProfessions[schoolID].push_back(professionIndex);
	}
	cout << "#" << endl;
	cout << differentProfessions << endl;

	cout << "#" << endl;
	for (i = 0; i < differentProfessions; i++) {
		if (professionSchools[i].size() == 1) {
			cout << professions[i] << endl;
		}
	}

	cout << "#" << endl;
	for (i = 0; i < differentProfessions; i++) {
		cout << professions[i] << " " << professionSchools[i].size() << endl;
	}

	cout << "#" << endl;
	mostProfSchoolID = 1;
	for (i = 1; i <= K; i++) {
		if (schoolProfessions[i].size() > schoolProfessions[mostProfSchoolID].size()) {
			mostProfSchoolID = i;
		}
	}
	cout << mostProfSchoolID << endl;

	cout << "#" << endl;
	cout << getSchoolsAfterMerge(schoolProfessions, K) << endl;

	return 0;
}

