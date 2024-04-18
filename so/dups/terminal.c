#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int main(int argc,char ** argv){
	int pd[2];
	pipe(pd);
	if(fork() == 0){
		dup2(pd[1],1);
		close(pd[0]);
		close(pd[1]);
		execlp("ls","ls","/etc",NULL);
		perror("ls");
		return 1;
	}else{
		dup2(pd[0],0);
		close(pd[0]);
		close(pd[1]);
		execlp("wc","wc","-l",NULL);
		perror("wc");
		return 1;
	}
	
	return 0;
}
