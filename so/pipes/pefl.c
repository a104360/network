#include <unistd.h>
#include <stdio.h>

int main(){
	
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
