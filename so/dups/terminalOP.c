#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <ctype.h>

typedef char * Command;
// Apresenta o comando na posicao N da pipeline de comandos
static int getIndexNCommand(const char *argv, int n) {
    if (n == 0)
        return 0;
    int size = strlen(argv);
    int count = 0;
    int i = 0;
    for (; i < size && count != n && argv[i] != '\0'; i++) {
        if (argv[i] == '|')
            count++;
    }
    if (argv[i] == '\0') // Correct the condition to check for end of string
        return 0;
    return i + 1;
}

// Corrigida e testada
void getReply(int fd, char *reply) {
    lseek(fd, 0, SEEK_SET);
    char chunk[128 + 1];
    int bytesRead;
    int totalBytesRead = 0;

    
    while ((bytesRead = read(fd, chunk, 128)) > 0) {
        // Terminar o chunk com \0
        chunk[bytesRead] = '\0';

        // Copiar o chunk para o Array de reposta
        strcpy(reply + totalBytesRead, chunk);

        // Atualizar o total de bytes lidos
        totalBytesRead += bytesRead;
    }

    if (bytesRead == -1) {
        perror("Erro a ler o descritor de ficheiro");
    }
}

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

static int countCommands(const char * cmd){
    if(!cmd || cmd[0] == '\0') return 0;
    int count = 1;
    for(int i = 0;cmd[i] != '\0';i++){
        if(cmd[i] == '|' && cmd[i+1] >= ' ' && cmd[i+1] <= '~') 
            count++;
    }
    return count;
}

static int countTokens(const char * cmd){
    if(!cmd || cmd[0] == '\0') return 0;
    int count = 1;
    short first = 0;
    for(int i = 0;cmd[i] != '\0' && cmd[i] != '|' && cmd[i+1] != '|';i++){
        if(cmd[i] == ' '){
            if(first == 0) continue;
            if(cmd[i+1] > ' ' && cmd[i+1] <= '~')
                count++;
            continue;
        }
        if(first == 0){
            first = 1;
        }
    }
    return count;
}


static char ** cmdTok(const char * cmd){
    if(!cmd || cmd[0] == '\0') return NULL;
    char * copy = strdup(cmd);
    char * token = NULL;
    char * saveptr = copy;
    
    int size = countTokens(cmd) + 1;
    
    char ** arraycmds = malloc(sizeof(char *) * size);
    
    token = strtok_r(copy," |\0",&saveptr);
    
    arraycmds[0] = strdup(token);
    
    for(int i = 1;i < size-1;i++){
        token = strtok_r(NULL," |\0",&saveptr);
        if(token == NULL && i < size - 1) token = strtok_r(NULL," |\0",&saveptr);
        arraycmds[i] = strdup(token);
    }
    
    if(copy) free(copy);
    arraycmds[size - 1] = NULL;
    return arraycmds;
}

static char *** parsePipe(const char * cmdPipe){
    if(!cmdPipe || (cmdPipe[0] < ' ' || cmdPipe[0] > '~')) return NULL;
    
    char * cmdPipeCopy = strdup(cmdPipe);
    char * cmdPipeToken = NULL;
    char * saveptr = cmdPipeCopy;
    int parseArraySize = countCommands(cmdPipe) + 1; // Determinar quantos comandos vao ser executados
    
    char *** parseArray = malloc(sizeof(char **) * parseArraySize);
    
    cmdPipeToken = strtok_r(cmdPipeCopy,"|\0\n",&saveptr);

     parseArray[0] = cmdTok(cmdPipeToken);
    
    // Ciclo itera por quantos comandos houver
    for(int i = 1;i < parseArraySize;i++){
        cmdPipeToken = strtok_r(NULL,"|\0\n",&saveptr);
        parseArray[i] = cmdTok(cmdPipeToken);
    }
    if(cmdPipeCopy) free(cmdPipeCopy);
    parseArray[parseArraySize - 1] = NULL;
    return parseArray;
}

static void printCmds(char ** cmd){
    int i = 0;
    while(cmd[i] != NULL){
        printf("%s ",cmd[i]);
        i++;
    }
    putchar('\n');
}


static void freeCmdTok(char ** tok) {
    int i = 0;
    while(tok[i]){
        free(tok[i]);
        i++;
    }
    free(tok);
}


   
int main(int argc,char ** argv){

    switch (argc)
    {
    case 1:
        //perror("Não foram inseridos argumentos suficientes");
        char ** comandoPrimal = cmdTok("ls -l .");
        if(fork() == 0){
            execvp(comandoPrimal[0],comandoPrimal);
            perror("exec correu mal");
            freeCmdTok(comandoPrimal);
            _exit(-1);
        }
        wait(NULL);
        printf("Passa do exec se for no pai\n");
        freeCmdTok(comandoPrimal);
        break;
    default:
        int n = countCommands(argv[1]);
        if(n <= 0){
            perror("Não introduziu comandos validos\n");
            return -1;
        }

        if(n == 1){
            Command ** comandos = parsePipe(argv[1]);
            if(fork() == 0){
                execvp(comandos[0][0],comandos[0]);
                perror("Nao executou a funcao corretamente");
                _exit(-1);
            }
            wait(NULL);
            freeCmdTok(comandos[0]);
            return -1;
        }

        if(n == 2){
            Command ** comandos = parsePipe(argv[1]);
            int pd[2];
            pipe(pd);
            if(fork() == 0){
                dup2(pd[1],STDOUT_FILENO);
                close(pd[0]);
                close(pd[1]);
                execvp(comandos[0][0],comandos[0]);
                perror("Não executou corretamente");
                _exit(-1);
            }
            dup2(pd[0],STDIN_FILENO);
            close(pd[0]);
            close(pd[1]);
            execvp(comandos[1][0],comandos[1]);
            freeCmdTok(comandos[0]);
            freeCmdTok(comandos[1]);
            return 1;
        }
        if(n == 3){
            Command ** comandos = parsePipe(argv[1]);

            int pid1[2];
            int pid2[2];
            pipe(pid1);
            pipe(pid2);
            if(fork() == 0){
                dup2(pid1[1],STDOUT_FILENO);
                close(pid1[0]);
                close(pid1[1]);
                close(pid2[0]);
                close(pid2[1]);
                execvp(comandos[0][0],comandos[0]);
                perror("erro no comando 1");
                _exit(-1);
            }else{
                //wait(NULL);
                if(fork() == 0){
                    dup2(pid1[0],STDIN_FILENO);
                    close(pid1[0]);
                    close(pid1[1]);

                    dup2(pid2[1],STDOUT_FILENO);
                    close(pid2[0]);
                    close(pid2[1]);

                    execvp(comandos[1][0],comandos[1]);
                    perror("erro no comando 2");
                    _exit(-1);
                }else{
                    //wait(NULL);
                    if(fork() == 0){
                        dup2(pid2[0],STDIN_FILENO);
                        close(pid2[0]);
                        close(pid2[1]);
                        close(pid1[0]);
                        close(pid1[1]);
                        execvp(comandos[2][0],comandos[2]);
                        perror("Erro no ultimo comando\n");
                        _exit(-1);
                    }else{
                        wait(NULL);
                        close(pid1[0]);
                        close(pid1[1]);
                        close(pid2[0]);
                        close(pid2[1]);
                        freeCmdTok(comandos[0]);
                        freeCmdTok(comandos[1]);
                        freeCmdTok(comandos[2]);
                    }
                }
            }
            return 0;
            
        }else{
            perror("Ainda nao foi implementado para mais argumentos");
        }
        //perror("nao implementado");
        return 69;
        break;
    }

    return 0;
}