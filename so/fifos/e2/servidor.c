#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>

int bd[5][5] ={
	{3 , 8 , 2 , 5 , 7},
	{1 , 6 , 3 , 9 , 4},
	{2 , 8 , 0 , 3 , 5},
	{7 , 2 , 6 , 1 , 4},
	{9 , 3 , 7 , 2 , 8}
};

static int repetitions(int n){
	int a = 0;
	int status[5];
	for(int i = 0; i < 5;i++){
		if((status[i] = fork()) == 0){
			int count = 0;
			for(int j = 0; j < 5;j++){
				if(bd[i][j] == n) count++;
			}
			_exit(count);
		}
	}
	for(int i = 0; i < 5;i++){
		wait(&status[i]);
		if(WIFEXITED(status[i]))
			a += WEXITSTATUS(status[i]);
	} 
	return a;

}



static int stringToInt(char *str) {
    int result = 0;
    int i = 0;

    // Check for negative sign
    int sign = 1;
    if (str[0] == '-') {
        sign = -1;
        i = 1;
    }

    // Iterate through each character of the string and build the integer
    for (; str[i] != '\0'; i++) {
        if (str[i] >= '0' && str[i] <= '9') {
            result = result * 10 + (str[i] - '0');
        } else {
            // Invalid character encountered
            printf("Invalid character detected: %c\n", str[i]);
	    return -123;
        }
    }

    return result * sign;
}



int main(){
	const char fifo[11] = "./tmp/fifo";
	if(mkfifo("./tmp/fifo",0666) == -1) perror("Fifo não foi criado corretamente");
	
	int server = open(fifo,O_RDONLY);

	//printf("Não executa mais nada até o cliente abrir para escrever");
	char buffer[128];
	read(server,&buffer,sizeof(buffer));
	
	//close(server);

	printf("%s",buffer);
	
	close(server);
	
	int n = stringToInt(buffer);

	n = repetitions(n);
	
	printf("%d",n);
	/*
	mkfifo("./tmp/reply",0666);

	int reply = open("./tmp/reply",O_WRONLY);

	write(reply,&n,sizeof(n));

	close(reply);

	char end = '\0';
	
	write(reply,&end,sizeof(end));

	close(server);
	*/
	return 0;
}
