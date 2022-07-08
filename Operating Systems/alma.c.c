#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <unistd.h>
#include <mqueue.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>
#include <time.h>
#include <sched.h>
#include <signal.h>
#include <semaphore.h>
#define ASSISTANT_NUM 2
#define URSULA 0
#define BEAKMASTER 1
#define MSGSIZE 40
#define MAXMSGS 5
#define VALUE 1234

char *assistantNames[] = { "Ursula", "Beakmaster" };
int pipefd[2][2]; // file descriptor for two pipe
int numberOfWaiting = 0;

// parent's signal hander
void parentHandler(int signo, siginfo_t *info, void *ignored);
// child process, send signal to parent and read number of patients
void childProcess(int childNo, int(*pipefd)[2], char *mqname, struct mq_attr *attr);
// fork child process, return child's pid in parent process, else return 0
pid_t forkChild(int childNo, int(*pipefd)[2], char *mqname, struct mq_attr *attr);

int main(int argc, char **argv) {
	sem_t lock;
	int sickNum;
	int vaccinatedNum;
	int tempa, tempb, i;
	struct sigaction act;
	pid_t childPid;
	int notVaccinatedNum;
	struct mq_attr attr;
	sigset_t set;
	char *datafile_name = "clinic_data.txt"; // write file for save patients information
	char *mqname = "/almafa"; // mqname must start with / !!!!!
	char rcv_buf[MSGSIZE];
	mqd_t mqdes1;
	pid_t pid, cpid;
	int status;
	FILE* file;

	if (argc <= 1) {
		printf("No command argument, please run like ./a.out 10\n");
		return 1;
	}
	numberOfWaiting = atoi(argv[1]);
	if (numberOfWaiting <= 0) {
		printf("Number of waiting patients is <= 0\n");
		return 1;
	}

	// setup message queue
	//memset(&attr, 0, sizeof( attr));
	attr.mq_maxmsg = MAXMSGS; // < /proc/sys/fs/mqueue/msg_max
	attr.mq_msgsize = MSGSIZE;// < /proc/sys/fs/mqueue/msgsize_max
	mq_unlink(mqname); // remove if exists
	mqdes1 = mq_open(mqname, O_CREAT | O_RDWR, 0600, &attr);

	// parent process setup handle signal
	sigemptyset(&set);
	act.sa_flags = SA_SIGINFO;
	act.sa_mask = set; // every signal is accepted
	act.sa_sigaction = parentHandler;
	if (sigaction(SIGUSR1, &act, 0) == -1) { //display error message and quit if fail call sigaction
		perror("fail sigaction");
		exit(1);
	}

	if (forkChild(URSULA, pipefd, mqname, &attr) > 0) { // fork first child
		if (forkChild(BEAKMASTER, pipefd, mqname, &attr) > 0) { // fork second child
			// wait all child process exit
			while (wait(NULL) > 0) {
			}
		} else {
			exit(0);
		}
		// parent process read child's information by message queue
		sickNum = 0;
		vaccinatedNum = 0;
		for (i = 0; i < ASSISTANT_NUM; i++) {
			mq_receive(mqdes1, rcv_buf, MSGSIZE, 0);
			// printf("Parent received: %s\n", rcv_buf);
			sscanf(rcv_buf, "%d %d", &tempa, &tempb);
			sickNum += tempa;
			vaccinatedNum += tempb;
		}
		printf("Dr Bubo get these data at the end of the day: Number of sick: %d, Number of vaccinated: %d\n", sickNum, vaccinatedNum);
		// he writes in a file, who came for vaccination, how many did not receive and how many received vaccination
		sem_init(&lock, 0, 1); // protect the file by semaphore
		sem_wait(&lock);
		file = fopen(datafile_name, "w");
		fprintf(file, "number of patients, number of not vaccinated, number of vaccinated\n");
		fprintf(file, "%d, %d, %d\n", numberOfWaiting, sickNum, vaccinatedNum);
		printf("Dr Bubo writes in the file: %s\n", datafile_name);
		fclose(file);
		sem_post(&lock);
	} else {
		exit(0);
	}
	return 0;
}

// parent's signal hander
void parentHandler(int signo, siginfo_t *info, void *ignored) {
	int nums[2];
	int beakmasterNum;
	nums[0] = numberOfWaiting / 2; // ursula
	nums[1] = numberOfWaiting - nums[0]; // beakmaster

	int childNo = info->si_int;
	printf("Dr Bubo distributes %d patients to %s.\n", nums[childNo], assistantNames[childNo]);
	// send person data to bus using pipe
	write(pipefd[childNo][1], &nums[childNo], sizeof(int)); // send number of patients
	wait(NULL);
}

// child process, send signal to parent
void childProcess(int childNo, int(*pipefd)[2], char *mqname, struct mq_attr *attr) {
	char buffer[MSGSIZE];
	int sickNum;
	int vaccinatedNum;
	mqd_t mqdes2;
	union sigval val;
	int patientCount;
	val.sival_int = childNo;
	sigqueue(getppid(), SIGUSR1, val); // send signal to parent
	// read number of patients by pipeline
	read(pipefd[childNo][0], &patientCount, sizeof(int));
	printf("Assistant %s get %d patients.\n", assistantNames[childNo], patientCount);
	// tell how many were sick and how many were actually vaccinated by message queue
	mqdes2 = mq_open(mqname, O_CREAT | O_RDWR, 0600, attr);
	srandom(time(NULL));
	sickNum = random() % (patientCount + 1);
	if (sickNum > patientCount) {
		sickNum = patientCount;
	}
	vaccinatedNum = patientCount - sickNum;
	sprintf(buffer, "%d %d", sickNum, vaccinatedNum);
	mq_send(mqdes2, buffer, MSGSIZE, 30);
}

// fork child process, return child's pid in parent process, else return 0 in child process
pid_t forkChild(int childNo, int(*pipefd)[2], char *mqname, struct mq_attr *attr) {
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
		// call child process's code
		childProcess(childNo, pipefd, mqname, attr);
		return pid;
	} else {
		// return to parent process
		return pid;
	}
}

