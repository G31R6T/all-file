#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <wait.h>


int main (int argc,char* argv[]) {

    int pid;
    key_t key;
    int sh_mem_id;
    char *s;
	// key creation
    key=ftok(argv[0],1);
    // create shared memory for reading and writing (500 bytes )
    sh_mem_id=shmget(key,500,IPC_CREAT|S_IRUSR|S_IWUSR);
    // to connect the shared memory, 
    s = shmat(sh_mem_id,NULL,0);
    //
    pid = fork();
    if(pid>0){    
  
       char buffer[] = "Hurray Fradi! \n";                   
       // copy the value into the shared memory 
       strcpy(s,buffer);
       // release the memory
       shmdt(s);	   
//	s[0]='B';  //this causes a segmentation fault 
       wait(NULL); 
	// IPC_RMID- to clear the shared memory
       shmctl(sh_mem_id,IPC_RMID,NULL);
    } 
    else 
	if ( pid == 0 ) 
	{
	sleep(1);	              
        printf("Child: %s",s);
	// it releases the shared memory 
        shmdt(s);
	}

   return 0;
}
