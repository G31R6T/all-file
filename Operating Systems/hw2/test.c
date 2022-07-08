#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>
#include <signal.h>
#define MAX_ROWS 1000
#define MAX_NAME_LENGTH 50
#define MAX_DATE_LENGTH 15
#define MAX_PHONE_LENGTH 15
#define FIGHT_UP SIGUSR1

struct Record {
	char name[MAX_NAME_LENGTH];
	char birthDate[11];
	char phoneNumber[15];
	int isAcceptableExtraCharge;
	int vaccinated; // 1: vaccinated, 0: not vaccinated
};
int pipefd[2][2]; // file descriptor for two pipe
struct Record records[MAX_ROWS];
int size;

// load data from file
void loadList(struct Record* records, int* size);
// save data to file
void saveList(struct Record* records, int size);
// display records list
void displayList(struct Record* records, int size);
// get the number of not vaccinated
int getNotVaccinatedCount(struct Record* records, int size);
// parent's signal hander
void parentHandler(int signo, siginfo_t *info, void *ignored);
// child process, send signal to parent and pick patient
void childProcess(int childNo, int(*pipefd)[2]);
// fork child process, return child's pid in parent process, else return 0
pid_t forkChild(int childNo, int(*pipefd)[2]);

int main(int argc, char **argv) {
	struct sigaction act;
	pid_t childPid;
	int notVaccinatedNum;
	size = 0; // initial record list size is 0
	loadList(records, &size); // read person list
	//	displayList(records, size);
	notVaccinatedNum = getNotVaccinatedCount(records, size); // get the number of person who want vaccination

	// parent process setup handle signal
	sigset_t set;
	sigemptyset(&set);
	act.sa_flags = SA_SIGINFO;
	act.sa_mask = set; // every signal is accepted
	act.sa_sigaction = parentHandler;
	if (sigaction(SIGUSR1, &act, 0) == -1) { //display error message and quit if fail call sigaction
		perror("fail sigaction");
		exit(1);
	}

	if (notVaccinatedNum <= 4) {
		printf("Don't start vaccination bus as there are not more than 4 person,\n");
		printf("the number of want vaccination: %d\n", notVaccinatedNum);
		exit(0);
	} else {
		if (forkChild(0, pipefd) > 0) { // fork first child, return value > 0 in parent process
			if (notVaccinatedNum >= 10) {
				if (forkChild(1, pipefd) > 0) { // fork second child, return value > 0 in parent process
					// wait all child process exit
					while (wait(NULL) > 0) {
					}
				}
			} else {
				// wait all child process exit
				while ((childPid = wait(NULL)) > 0) {
					printf("childPid: %d\n", childPid);
				}
			}
		}
	}
	return 0;
}

// load data from file
void loadList(struct Record* records, int* size) {
	FILE *fp;
	int personCount = 0;
	char fileName[30] = "list.txt";
	fp = fopen(fileName, "r");
	if (fp == NULL) {
		printf("Not exists file: %s\n", fileName);
		exit(1);
	}
	while (personCount < MAX_ROWS && fscanf(fp, "%s", records[*size].name) == 1) {
		// this is vaccinated if first column is "VACCINATED"
		if (strcmp(records[*size].name, "VACCINATED") == 0) {
			records[*size].vaccinated = 1;
			// second column is name for vaccinated record
			if (fscanf(fp, "%s", records[*size].name) != 1) {
				break;
			}
		} else {
			records[*size].vaccinated = 0;
		}
		if (fscanf(fp, "%s %s %d", records[*size].birthDate, records[*size].phoneNumber,
				&records[*size].isAcceptableExtraCharge) == 3) {
			*size += 1;
		} else {
			break;
		}
		personCount++;
	}
	fclose(fp);
}

// save data to file
void saveList(struct Record* records, int size) {
	int i;
	FILE *fp;
	char fileName[30] = "list.txt";
	fp = fopen(fileName, "w");
	for (i = 0; i < size; i++) {
		if (records[i].vaccinated) {
			fprintf(fp, "%-12s", "VACCINATED");
		}
		fprintf(fp, "%-20s%-15s%-15s%d", records[i].name, records[i].birthDate,
				records[i].phoneNumber, records[i].isAcceptableExtraCharge);
		fprintf(fp, "\n");
	}
	fclose(fp);
}

// display records list
void displayList(struct Record* records, int size) {
	int i;
	printf("%-12s%-20s%-15s%-15s%-15s\n", "Vaccinated", "Name", "Birth date", "Phone number",
			"Acceptable pay");
	for (i = 0; i < size; i++) {
		if (records[i].vaccinated) {
			printf("%-12s", "VACCINATED");
		} else {
			printf("%-12s", "NO");
		}
		printf("%-20s%-15s%-15s", records[i].name, records[i].birthDate, records[i].phoneNumber);
		if (records[i].isAcceptableExtraCharge) {
			printf("yes\n");
		} else {
			printf("no\n");
		}
	}
}

// get the number of not vaccinated
int getNotVaccinatedCount(struct Record* records, int size) {
	int result = 0;
	int i;
	for (i = 0; i < size; i++) {
		if (!records[i].vaccinated) {
			result++;
		}
	}
	return result;
}

// parent's signal hander
void parentHandler(int signo, siginfo_t *info, void *ignored) {
	int i;
	int personCount = 0;
	int personIndexs[5] = { 0 };
	int childNo = info->si_int;
	char pipeBuffer[100] = { 0 };

	// the Operation Committee reads the next persons data(5 or 10), writes them on the screen
	printf("Operation Committee reads these persons data who want vaccination for bus%d:\n",
			childNo + 1);
	printf("%-20s%-15s%-15s%-15s\n", "Name", "Birth date", "Phone number", "Acceptable pay");
	for (i = 0; i < size && personCount < 5; i++) {
		if (!records[i].vaccinated) {
			// print every person who want vaccination on the screen
			printf("%-20s%-15s%-15s", records[i].name, records[i].birthDate, records[i].phoneNumber);
			if (records[i].isAcceptableExtraCharge) {
				printf("yes\n");
			} else {
				printf("no\n");
			}
			personCount++;
		}
	}
	printf("\n");

	// send person data to bus using pipe
	write(pipefd[childNo][1], &personCount, sizeof(int)); // send to bus the number of person
	personCount = 0;
	for (i = 0; i < size && personCount < 5; i++) { // send person data
		if (!records[i].vaccinated) {
			// print every person who want vaccination on the screen
			sprintf(pipeBuffer, "%-20s%-15s%-15s", records[i].name, records[i].birthDate,
					records[i].phoneNumber);
			if (records[i].isAcceptableExtraCharge) {
				strcat(pipeBuffer, "yes\n");
			} else {
				strcat(pipeBuffer, "no\n");
			}
			personIndexs[personCount] = i;
			personCount++;
			write(pipefd[childNo][1], pipeBuffer, 100);
		}
	}
	// send person's index to bus using pipe
	write(pipefd[childNo][1], personIndexs, sizeof(int) * personCount);
	wait(NULL); // wait child process pick patients
	// read arrived patients index, add a "VACCINATED" remark to everybody's data
	read(pipefd[childNo][0], personIndexs, sizeof(int) * personCount);
	for (i = 0; i < personCount; i++) {
		if (personIndexs[i] >= 0) {
			records[personIndexs[i]].vaccinated = 1;
		}
	}
	// save changed records
	saveList(records, size);
}

// child process, send signal to parent and pick patient
void childProcess(int childNo, int(*pipefd)[2]) {
	int i;
	double randNum;
	union sigval val;
	int personCount;
	int personIndexs[5] = { 0 };
	val.sival_int = childNo;
	sigqueue(getppid(), FIGHT_UP, val);

	// read persons data
	char pipeReadBuffer[100] = { 0 };
	read(pipefd[childNo][0], &personCount, sizeof(int));
	printf("Bus%d get these person data:\n", childNo + 1);
	for (i = 0; i < personCount; i++) {
		read(pipefd[childNo][0], pipeReadBuffer, 100);
		printf("%s", pipeReadBuffer);
	}
	// read persons index
	read(pipefd[childNo][0], personIndexs, sizeof(int) * personCount);
	// randomly pick patients, arrive in time to the vaccination spot with the rate of 90%
	printf("These patients arrived in time: ");
	srandom(time(NULL));
	for (i = 0; i < personCount; i++) {
		randNum = (double) random() / RAND_MAX;
		if (randNum > 0.9) {
			personIndexs[i] = -1; // this patient is not arrived in time
		} else {
			printf("%s ", records[personIndexs[i]].name);
		}
	}
	printf("\n\n");
	// send arrived person's index to parent process
	write(pipefd[childNo][1], personIndexs, sizeof(int) * personCount);
}

// fork child process, return child's pid in parent process, else return 0
pid_t forkChild(int childNo, int(*pipefd)[2]) {
	pid_t pid;

	// create pipe, display error message and exit if fail create pipe
	if (pipe(pipefd[childNo]) < 0) {
		printf("fail create pipe\n");
		exit(1);
	}

	pid = fork();
	// display error message and quit if fail create child process
	if (pid < 0) {
		perror("Fail fork");
		exit(1);
	} else if (pid == 0) { // child process
		// child process, send signal to parent and pick patient
		childProcess(childNo, pipefd);
		return pid;
	}
	return pid;
}
