#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include "defs.h"
#include "vector.h"

//FIFO criado pelo servidor
//Cliente pode receber um sigpipe (concorrência!)

const char request[] = "./fifo";

int main (int argc, char * argv[]){

	init_vector();
	print_vector();
	
	mkfifo(request,0666);
	
	

	unlink(request);
	
	return 0;
}
