#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>


/*void controller(int argc,char ** argv){
	int status;
	pid_t son;
	for(int i = 0; i < argc;i++){
		son = fork();
		if(son == 0){
			execvp(argv[i],&argv[i]);
			perror("N executou corretamente\n");
			_exit(-1);
		}
	}

	for(int i = 0, i < argc;i++){
		son = wait(&status);
		if(WIFEXITED(status)){
			
		}
	}
}*/

int main(int argc, char ** argv){
/*	int status;
	pid_t son[argc];
	int i, quantos = argc;

	for(i = 0; i < argc;i++){
		if((son[i] = fork))		
	}
*/
	int status;
	pid_t son;
	repeat:
	for(int i = 1;i < argc;i++){
		son = fork();
		if(son == 0){
			execvp(argv[i],&argv[i]);
			perror("N fechou corretamente");
			_exit(-1);
		}
	}
	
	for(int i = 0; i < argc - 1;i++){
		son = wait(&status);
		sleep(1);
		if(WIFEXITED(status)){
			if(WEXITSTATUS(status) == 0){
				printf("Calhou 0\n");
			} else {
				for(int j = i;j < argc - 1;j++){
					son = wait(&status);
				}
				goto repeat;
				break;
			}
		}
		
	}
	return 0;
}
