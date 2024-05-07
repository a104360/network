#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <ctype.h>

typedef char * Command;
typedef Command * Query;

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


static void freeCmdTok(char ** tok) {
    int i = 0;
    while(tok[i]){
        free(tok[i]);
        i++;
    }
    free(tok);
}


   
int main(int argc,char ** argv){ // ./terminalFinal "..."
    if (argc != 2) {
        fprintf(stderr, "Usage: %s \"command1 | command2 | ... | commandN\"\n", argv[0]);
        return 1;
    }

    int nCommands = countCommands(argv[1]);
    Query *commands = parsePipe(argv[1]);

    int oldPipe[2], newPipe[2];
    // Para cada comando, executar o ciclo
    for (int i = 0; i < nCommands; i++) {
        // Em todos os comandos, anteriores ao ultimo, cria-se um pipe
        if (i < nCommands - 1) {
            pipe(newPipe);
        }   

        if (fork() == 0) {
            if (i > 0) {  // Assim não executa o primeiro
                dup2(oldPipe[0], STDIN_FILENO);
                close(oldPipe[0]);
                close(oldPipe[1]);
            }
            if (i < nCommands - 1) {  // Assim não vai executar o último comando
                dup2(newPipe[1], STDOUT_FILENO);
                close(newPipe[0]);
                close(newPipe[1]);
            }
            execvp(commands[i][0], commands[i]);
            perror("execvp falhou");
            _exit(-1);
        } // Adicionar aqui verificação de erro

        // O pai fecha os file descriptors do pipe antigo
        if (i > 0) {
            close(oldPipe[0]);
            close(oldPipe[1]);
        }
        // Para todos os comandos, exceto o último,
        // cada descritor torna-se os respetivos novos descritores
        if (i < nCommands - 1) {
            oldPipe[0] = newPipe[0];
            oldPipe[1] = newPipe[1];
        }

        // Espera para que o comando execute
        wait(NULL);
    }

    // Libertar memória da Query
    for(int i = 0;commands[i];i++){
        freeCmdTok(commands[i]);
    }
    if(commands) free(commands);
    return 0;
}