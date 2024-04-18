#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>

int main(int argc,char ** argv){

    int passwd = open("/etc/passwd",O_RDONLY);

    if(passwd == -1){
        perror("Erro ao abrir ficheiro /etc/passwd\n");
        return 1;
    }

    if(dup2(passwd,STDIN_FILENO) == -1){
        perror("Erro ao redirecionar file descriptor\n");
        return 2;
    }

    int output = open("saida.txt",O_CREAT | O_TRUNC | O_WRONLY,0633);
    if(output == -1){
        perror("Erro ao abrir o output\n");
        return 3;
    }
    


    // impressao de termino
    write(STDOUT_FILENO,"terminei\n",10);
    return 0;
}