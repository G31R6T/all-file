       #include <stdio.h>
       #include <stdlib.h>
       #include <unistd.h> // for pipe()
       #include <string.h>
	//
	// unnamed pipe example
	//
       int main(int argc, char *argv[])
       {
           int pipefd[2]; // unnamed pipe file descriptor array
           pid_t pid;
           char sz[100];  // char array for reading from pipe

           if (pipe(pipefd) == -1) 
	   {
               perror("Opening error!");
               exit(EXIT_FAILURE);
           }
           pid = fork();	// creating parent-child processes
           if (pid == -1) 
	   {
               perror("Fork error");
               exit(EXIT_FAILURE);
           }

           if (pid == 0) 
	   {		    	// child process
	       sleep(3);	// sleeping a few seconds, not necessary
           close(pipefd[1]);  //Usually we close the unused write end
	       printf("Child starts to read from the pipe!\n");
           read(pipefd[0],sz,sizeof(sz)); // reading max 100 chars
           printf("Child read the message: %s\n",sz);
	       printf("\n");
           close(pipefd[0]); // finally we close the used read end
           } 
           else 
           {    // Parent process 
               printf("Parent starts!\n");
               close(pipefd[0]); //Usually we close unused read end
               write(pipefd[1], "Hurray Fradi!",13); //Hungarian football team
               close(pipefd[1]); // Closing write descriptor 
               printf("Parent wrote the message to the pipe!\n");
               fflush(NULL); 	// flushes all write buffers (not necessary)
               wait();		// waiting for child process (not necessary)
				// try it without wait()
	       printf("Parent finished!\n");	
	   }
	   exit(EXIT_SUCCESS);	// force exit, not necessary
       }

 
