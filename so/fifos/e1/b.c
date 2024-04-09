#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>

int main(int argc,char ** argv){
	int fd = open("./fifo",O_WRONLY);
	
	for(int i = 1;i < argc;i++){
		if(write(fd,argv[i],sizeof(argv[i])) == -1)
			perror("Houve problema a escrever no fifo");
	}
	return 0;
}
