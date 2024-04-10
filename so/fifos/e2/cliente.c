#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>


int main(int argc,char ** argv){
	
	const char fifo[11] = "./tmp/fifo";
	
	int request = open(fifo,O_WRONLY);
	
	int n = 3;

	write(request,&n,sizeof(int));
	
	printf("%d requested to the server\n",n);

	if(close(request) == -1) perror("Não fechou o fifo de request direito");

	int reply = open("./tmp/reply",O_RDONLY);

	int answer;
	read(reply,&answer,sizeof(answer));

	printf("Existem %d instancias na tabela\n",answer);

	close(reply);
	
	return 0;
}
