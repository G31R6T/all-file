#include <stdio.h>
#include <string.h>
#define MAX_ROWS 500
#define MAX_NAME_LENGTH 50
#define MAX_DATE_LENGTH 15
#define MAX_PHONE_LENGTH 15

struct Record {
	char name[MAX_NAME_LENGTH];
	char birthDate[11];
	char phoneNumber[15];
	int isAcceptableExtraCharge;
};

// add new data
void addNewData(struct Record* records, int* size);
// modify data
void modifyData(struct Record* records, int* size);
// delete data
void deleteData(struct Record* records, int* size);
// create total list
void listData(struct Record* records, int* size);
// query record by name, return the index
int queryByName(struct Record* records, int* size, char* name);
// read string with limited size
void readString(char *stringName, char *s, int maxSize);

int main() {
	struct Record records[MAX_ROWS];
	int size;
	int option;
	option = 1;
	size = 0; // initial record list size is 0

	// display menu, quit the program until user enter option 5
	while (option != 5) {
		// display menu
		printf("1. add new data\n");
		printf("2. modify data\n");
		printf("3. delete data\n");
		printf("4. create total list\n");
		printf("5. quit\n");

		// ask user enter option
		printf("Enter option: ");

		// display error message if the input option is invalid
		if (scanf("%d", &option) != 1 || !(option >= 1 && option <= 5)) {
			printf("Invalid option!\n");
		}
		while (getchar() != '\n')
			; // consume input

		if (option == 1) {
			addNewData(records, &size);
		} else if (option == 2) {
			modifyData(records, &size);
		} else if (option == 3) {
			deleteData(records, &size);
		} else if (option == 4) {
			listData(records, &size);
		}
		// print newline
		printf("\n");
	}
}

// add new data
void addNewData(struct Record* records, int* size) {
	// ask user enter name, date of birth and the phone number
	printf("Enter name: ");
	readString("name", records[*size].name, MAX_NAME_LENGTH);

	// abort add new data if this name exists
	if (queryByName(records, size, records[*size].name) >= 0) {
		printf("This name is exists!\n");
		return;
	}

	printf("Enter date of birth: ");
	readString("date of birth", records[*size].birthDate, MAX_DATE_LENGTH);
	printf("Enter phone number: ");
	readString("phone number", records[*size].phoneNumber, MAX_PHONE_LENGTH);
	printf(
			"Do you want to get it free or it is acceptable to pay extra charge? (0 for free, 1 for pay): ");
	scanf("%d", &records[*size].isAcceptableExtraCharge);

	// add size after add new data
	*size += 1;
	printf("Success add data.\n");
}

// modify data
void modifyData(struct Record* records, int* size) {
	char name[MAX_NAME_LENGTH];
	int index;

	// ask user enter name
	printf("Enter name to modify: ");
	readString("name", name, MAX_NAME_LENGTH);
	index = queryByName(records, size, name);
	// abort modify data if can't find this name
	if (index < 0) {
		printf("This name not exists!\n");
		return;
	}

	// ask user new date of birth and the phone number
	printf("Enter new date of birth: ");
	readString("date of birth", records[index].birthDate, MAX_DATE_LENGTH);
	printf("Enter new phone number: ");
	readString("phone number", records[index].phoneNumber, MAX_PHONE_LENGTH);
	printf(
			"Do you want to get it free or it is acceptable to pay extra charge? (0 for free, 1 for pay): ");
	scanf("%d", &records[index].isAcceptableExtraCharge);
	printf("Success modify data.\n");
}

// delete data
void deleteData(struct Record* records, int* size) {
	int i;
	char name[MAX_NAME_LENGTH];
	int index;
	char confirm[10] = "";

	// ask user enter name
	printf("Enter name to delete: ");
	readString("name", name, MAX_NAME_LENGTH);
	index = queryByName(records, size, name);
	// abort modify data if can't find this name
	if (index < 0) {
		printf("This name not exists!\n");
		return;
	}

	// confirm with user to delete this record
	while (!(strcmp(confirm, "y") == 0 || strcmp(confirm, "n") == 0)) {
		printf("Are you confirm delete this data(y/n)? ");
		readString("confirm", confirm, 10);
	}
	// delete data
	if (strcmp(confirm, "y") == 0) {
		// move data after this index ahead
		for (i = index + 1; i < *size; i++) {
			records[i - 1] = records[i];
		}
		*size -= 1;
		printf("Success delete data.\n");
	} else {
		printf("Abort delete data.\n");
	}
}

// create total list
void listData(struct Record* records, int* size) {
	int i;
	FILE *fp;
	char outName[30] = "out.txt";
	fp = fopen(outName, "w");
	printf("%-20s%-15s%-15s%-15s\n", "Name", "Birth date", "Phone number", "Acceptable pay");
	fprintf(fp, "%-20s%-15s%-15s%-15s\n", "Name", "Birth date", "Phone number", "Acceptable pay");
	for (i = 0; i < *size; i++) {
		printf("%-20s%-15s%-15s", records[i].name, records[i].birthDate, records[i].phoneNumber);
		fprintf(fp, "%-20s%-15s%-15s", records[i].name, records[i].birthDate, records[i].phoneNumber);
		if (records[i].isAcceptableExtraCharge) {
			printf("yes\n");
			fprintf(fp, "yes\n");
		} else {
			printf("no\n");
			fprintf(fp, "no\n");
		}
	}
	fclose(fp);
	printf("save list to file: %s\n", outName);
}

// read string with limited size
void readString(char *stringName, char *s, int maxSize) {
	int valid = 0;
	int i;
	// ask user enter string until the string is not empty
	while (!valid) {
		fgets(s, maxSize, stdin);
		// delete newline character
		for (i = 0; s[i] != '\0'; i++) {
			if (s[i] == '\n') {
				s[i] = '\0';
			}
		}
		// invalid input if the string is empty
		if (s[0] == '\0') {
			printf("Invalid %s, please enter again: ", stringName);
		} else {
			valid = 1;
		}
	}
}

// query record by name, return the index
int queryByName(struct Record* records, int* size, char* name) {
	int i;
	// return -1 for invalid name
	if (name == NULL) {
		return -1;
	}
	// find name in the list
	for (i = 0; i < *size; i++) {
		// return the index if find the name
		if (strcmp(records[i].name, name) == 0) {
			return i;
		}
	}
	return -1;
}

