#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>


int main(int argc,char ** argv){
	
	const char fifo[] = "./tmp/fifo";
	int request = open(fifo,O_WRONLY); // Abrir fifo para escrever
	
	// enviar o numero para o servidor
	int n = 3;
	write(request,&n,sizeof(int)); printf("%d requested to the server\n",n); 
	// fechar o servidor
	if(close(request) == -1) perror("Não fechou o fifo de request direito");

	
	int reply = open("./tmp/reply",O_RDONLY); // Abrir o fifo de resposta

	// ler a resposta
	int answer;
	read(reply,&answer,sizeof(answer)); printf("Existem %d instancias na tabela\n",answer);

	close(reply); // fechar o fifo de reposta
	
	return 0;
}
