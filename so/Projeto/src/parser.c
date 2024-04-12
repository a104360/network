#include "../include/parser.h"
#include <stdlib.h>
#include <string.h>

static char * concat(int argc,char ** argv, int start,int end){
    if(argc == 0) return NULL;
    if(start < 0) start = 0;
    int totalSize = 1;
    
    if(start == end) totalSize = strlen(argv[start]); 
    else for(int i = start;i < end && i < argc;i++) totalSize += strlen(argv[i]) + 1;

    char * string = malloc(sizeof(char) * totalSize);

    int counter = start;

    for(int i = 0; i < totalSize && counter <= end && counter < argc;){
        for(int j = 0;j < strlen(argv[counter]);j++){
            string[i] = argv[counter][j];
            i++;
        }
        if(i+1 != totalSize) {
            string[i] = ' ';
            i++;
        }
        counter++;
    }
    string[totalSize] = '\0';
    return string;
}

static int getCommandEnd(int argc, char ** argv,int start){
    int i = start;
    while(argv[i] != "|" && i < argc){
        i++;
    }
    return i;
}

char * parseCommand(int argc,char ** argv,int index){
    int last = getCommandEnd(argc,argv,index);
   char * command = NULL;
}