#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/wait.h>


static int a(){
	int pd[2];
        int m = 8;
        pipe(pd);

        if(fork() == 0){
                int v;
                close(pd[1]);
                read(pd[0],&v,sizeof(v));
                printf("filho: %d\n",v);
                _exit(0);
        }
        close(pd[0]);
        write(pd[1],&m,sizeof(m));
        close(pd[1]);
	return 0;
}


static int b(){
	int pd[2];
	int son;
	int m = 8;
	pipe(pd);
	
	if((son = fork()) == 0){
		close(pd[0]);
		write(pd[1],&m,sizeof(m));
		_exit(0);
		perror("Filho com deficiencia");
	}
	
	sleep(1);
	wait(&son);
	
	close(pd[1]);
	int v = 0;
	read(pd[0],&v,sizeof(v));
	
	
	return v;
}


int main(){
	
	if(a() == 0){
		sleep(1);
		write(STDOUT_FILENO,"a) concluido\n",13);
	}

	if(b() != 0){
		sleep(1);
		write(STDOUT_FILENO,"b) concluido\n",13);
	}

	

	return 0;
}
