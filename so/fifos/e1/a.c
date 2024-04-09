#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>

int main(){
	if(mkfifo("./fifo",0666) == -1){
		perror("Ocorreu um erro ao abrir o fifo");
		return 1;
	}
	
	return 0;
}	
