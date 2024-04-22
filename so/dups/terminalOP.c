#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <ctype.h>

typedef char * Command;


// Conta quantos comandos existem no pipeline passado como argumento
int countCommands(const char *argv) {
    if (argv == NULL || argv[0] == '\0')
        return 0;
    int count = 0;
    char *copy = strdup(argv);
    char *saveptr = NULL;
    char *token = strtok_r(copy, "|", &saveptr);
    while (token) {
        count++;
        token = strtok_r(NULL, "|", &saveptr);
    }
    free(copy);
    return count;
}

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

static char * getCmdName(char * command){
    int i = 0;
    while(command[i] != '\0' && command[i] != ' ') i++;
    char * name = malloc(sizeof(char)*i);
    for(int j = 0;j < i;j++){
        name[j] = command[j];
    }
    name[i] = '\0';
    return name;
}

// Separa cada comando do pipeline por ordem 
static char *cmdtok(const char *argv, int start, char *saveptr) {
    if (argv == NULL)
        return NULL;
    char *copy = strdup(argv + start); // Start from the given position
    if (saveptr == NULL)
        return strtok_r(copy, "|", &saveptr);
    saveptr = saveptr + 1;
    return strtok_r(NULL, "|", &saveptr);
}


char * parseUniqueCommand(const char * argv){
    return strdup(argv);
}

char ** parseMultipleCommands(const char * argv){
    if(argv == NULL) return NULL;
    int max = countCommands(argv);
    char ** args = malloc(sizeof(char *) * max);
    char * saveptr = NULL;
    for(int i = 0;i < max;i++){
        args[i] = cmdtok(argv,getIndexNCommand(argv,i),saveptr);
    }
    return args;
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

static int countTokens(const char * cmd){
    if(!cmd || cmd[0] == '\0') return 0;
    int count = 1;
    for(int i = 0;cmd[i] != '\0';i++){
        if(cmd[i] == ' ' && cmd[i+1] > ' ' && cmd[i+1] <= '~') count++;
    }
    return count;
}

static char ** parseCmdTok(const char * cmd){
    if(!cmd || (cmd[0] < ' ' || cmd[0] > '~')) return NULL;
    char * cmdCopy = strdup(cmd);
    char * token = NULL;
    char * saveptr = cmdCopy;
    int size = countTokens(cmd) + 1;
    char ** comandoTotal = malloc(sizeof(char *) * size);
    token = strtok_r(cmdCopy," \0",&saveptr);
    comandoTotal[0] = strdup(token);
    for(int i = 1;i < size - 1;i++){
        token = strtok_r(NULL," \0",&saveptr);
        comandoTotal[i] = strdup(token);
    }
    if(cmdCopy) free(cmdCopy);
    comandoTotal[size - 1] = NULL;
    return comandoTotal;
}


int main()
{
    const char comandofixo[] = "\n";
    int size = countTokens(comandofixo);
    printf("ls -l /etc : %d\n",size);
    
    char ** comando = parseCmdTok(comandofixo);
    if(!comando){
        perror("Nao ha comando");
        return 1;
    }
    for(int i = 0; i < size;i++){
        printf("argumento %d : %s\n",i,comando[i]);
    }
    
    for(int i = 0; i < size;i++){
        free(comando[i]);
    }
    free(comando);
    return 0;
}


/*static int indexCommandN(int argc,char ** argv,int index){
    int commandsCounter = 0;
    for(int i = 0; i < argc && commandsCounter != index;i++){
        if(strcmp(argv[i],"|") == 0) commandsCounter++;
    }
    return commandsCounter;
}

static int endCommandN(int argc,char ** argv,int index){
    int start = indexCommandN(argc,argv,index);
    if(start + 1 < )
}

static char * command(int argc,char ** argv,int index){
    int commandIndex = indexCommandN(argc,argv,index);

}

/// @brief contar comandos
/// @param argc Deve ser o argc da main - 1
/// @param argv argv da main normal
/// @return numero de comandos
static int countCommands(int argc, char ** argv){
    int count = 1;
    for(int i = 0;i < argc;i++){
        if(strcmp("|",argv[i]) == 0) count++;
    }
    return count;
}*/

/*int main(int argc,char ** argv){

    switch (argc)
    {
    case 1:
        //perror("Não foram inseridos argumentos suficientes");
        char * garg[6];
        garg[0] = "-l";
        execvp("ls",garg);
        break;
    default:
        int n = countCommands(argv[1]);
        if(n <= 0){
            return 0;
        }
        
        
        
        if(n == 1){
            Command comando = parseUniqueCommand(argv[1]);
            char * name = getCmdName(comando);
            execvp(name,&comando);
            free(name);
            free(comando);
            return 0;
        }

        if(n == 2){
            Command * comandos = parseMultipleCommands(argv[1]);
            char ** name = malloc(sizeof(char *) * 2);
            name[0] = getCmdName(comandos[0]);
            name[1] = getCmdName(comandos[1]);
            int pd[2];
            pipe(pd);
            if(fork() == 0){
                dup2(pd[1],STDOUT_FILENO);
                close(pd[0]);
                close(pd[1]);
                execvp(name[0],&comandos[0]);
                _exit(-1);
            }
            dup2(pd[0],STDIN_FILENO);
            close(pd[0]);
            close(pd[1]);
            execvp(name[1],&comandos[1]);
            free(name[0]);
            free(name[1]);
            free(name);
            free(comandos[0]);
            free(comandos[1]);
            free(comandos);
            return 1;
        }
        if(n == 3){
            Command * comandos = parseMultipleCommands(argv[1]);
            char ** name = malloc(sizeof(char *) * 3);
            name[0] = getCmdName(comandos[0]);
            name[1] = getCmdName(comandos[1]);
            name[2] = getCmdName(comandos[2]);

            int pid1[2];
            int pid2[2];
            pipe(pid1);
            pipe(pid2);
            if(fork() == 0){
                dup2(pid1[1],STDOUT_FILENO);
                close(pid1[0]);
                close(pid1[1]);
                execvp(name[0],&comandos[0]);
                perror("erro no comando 1");
                _exit(-1);
            }
            if(fork() == 0){

                dup2(pid1[0],STDIN_FILENO);
                close(pid1[0]);
                close(pid1[1]);

                dup2(pid2[1],STDOUT_FILENO);
                close(pid2[0]);
                close(pid2[1]);

                execvp(name[1],&comandos[1]);
                perror("erro no comando 2");
                _exit(-1);
            }
            else{
                dup2(pid2[0],STDIN_FILENO);
                close(pid2[0]);
                close(pid2[1]);
                execvp(name[2],&comandos[2]);
                perror("Erro no ultimo comando\n");
                _exit(-1);
            }
            if(name){

            }
        }else{
            perror("Ainda nao foi implementado para mais argumentos");
        }
        break;
    }

    return 0;
}*/