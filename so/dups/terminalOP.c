#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <ctype.h>

typedef char * Command;


// Conta quantos comandos existem no pipeline passado como argumento
/*int countCommands(const char * cmd) {
    if(!cmd || cmd[0] == '\0') return 0;
    int count = 1;
    for(int i = 0;cmd[i] != '\0';i++){
        if(cmd[i] == '|' && cmd[i+1] >= ' ' && cmd[i+1] <= '~') 
            count++;
    }
    return count;
}*/

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

/*
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
*/

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

int main(){
    const char p1[] = "ls";
    const char p2[] = "wc -l";
    const char p3[] = "ls | wc -l|fafa";

    printf("Numero de comandos nos pipes : \n1º : %d\n2º : %d\n3º : %d\n",countCommands(p1),countCommands(p2),countCommands(p3));

    char *** pipeline = parsePipe(p3);

    for(int i = 0;pipeline[i] != NULL;i++){
        printCmds(pipeline[i]);
        freeCmdTok(pipeline[i]);
    }
    free(pipeline); 
    
}


/******  EXECUTAR ESTE PARA TESTAR ******
   
int main(int argc,char ** argv){

    switch (argc)
    {
    case 1:
        //perror("Não foram inseridos argumentos suficientes");
        char ** comandoPrimal = parseCmdTok("ls -l .");
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
            return 0;
        }

        if(n == 1){
            int size = strlen(argv[1]);
            char comandoFinal[size];
            strcpy(comandoFinal,argv[1]);
            char * argumentos[2] = {comandoFinal,NULL}; 
            execvp(comandoFinal,argumentos);
            return -1;
        }

        if(n == 2){
            Command * comandos = parseCmdTok(argv[1]);
            int pd[2];
            pipe(pd);
            if(fork() == 0){
                dup2(pd[1],STDOUT_FILENO);
                close(pd[0]);
                close(pd[1]);
                execvp(comandos[0],&comandos[0]);
                perror("Não executou corretamente");
                _exit(-1);
            }
            dup2(pd[0],STDIN_FILENO);
            close(pd[0]);
            close(pd[1]);
            execvp(comandos[1],&comandos[1]);
            free(comandos[0]);
            free(comandos[1]);
            free(comandos);
            return 1;
        }
        if(n == 3){
            Command * comandos = parseCmdTok(argv[1]);

            int pid1[2];
            int pid2[2];
            pipe(pid1);
            pipe(pid2);
            if(fork() == 0){
                dup2(pid1[1],STDOUT_FILENO);
                close(pid1[0]);
                close(pid1[1]);
                execvp(comandos[0],&comandos[0]);
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

                execvp(comandos[1],&comandos[1]);
                perror("erro no comando 2");
                _exit(-1);
            }
            if(fork() == 0){
                dup2(pid2[0],STDIN_FILENO);
                close(pid2[0]);
                close(pid2[1]);
                execvp(comandos[2],&comandos[2]);
                perror("Erro no ultimo comando\n");
                _exit(-1);
            }
            wait(NULL);
            wait(NULL);
            wait(NULL);
            freeCmdTok(comandos);
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
*/