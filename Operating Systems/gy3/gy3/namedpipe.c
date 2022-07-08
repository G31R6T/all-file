#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h> // for errno, the number of last error

int main(int argc, char* argv[])
{
    int pid,fd;
    printf("Fifo start!\n");
    int fid=mkfifo("fifo.ftc", S_IRUSR|S_IWUSR ); // creating named pipe file
    // S_IWGRP, S_IROTH (other jog), file permission mode
    // the file name: fifo.ftc
    // the real fifo.ftc permission is: mode & ~umask 
    if (fid==-1)
    {
	printf("Error number: %i",errno);
	exit(EXIT_FAILURE);
    }
    printf("Mkfifo ends, fork starts!\n");
    pid = fork();
    
    if(pid>0)   //parent 
	{
	char s[1024]="Nothing";		
	printf("The result of pipe opening in parent: %d!\n",fid);
	fd=open("fifo.ftc",O_RDONLY);
	read(fd,s,sizeof(s));
	printf("I read from the pipe:  %s \n",s);
	close(fd);
	// remove fifo.ftc
	unlink("fifo.ftc");
        }
	else // child
	{
	printf("I am the child, I am writing into the pipe!\n");
	printf("The result of pipe opening in child: %d!\n",fid);
        fd=open("fifo.ftc",O_WRONLY);
        write(fd,"Hurray Fradi!\n",12);
        close(fd);
	printf("I am the child, I have finished working!\n");
    	}	
    
    return 0; 
}
