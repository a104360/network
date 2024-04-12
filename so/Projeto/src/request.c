#include "../include/request.h"
#include "../include/command.h"
#include <string.h>
#include <stdlib.h>

typedef struct request {
    int id;
    int time;
    Command ** commands;
    int argc;
};

Request * createRequest(int id,int time,Command ** commands,int argc){
    Request * request = malloc(sizeof(struct request));
    request->id = id;
    request->time = time;
    request->argc = argc;
    request->commands = malloc(sizeof(Command *));
    for(int i = 0;i < argc;i++){
        request->commands = copyCommand(commands[i]);        
    }
    return request;
}

int getRid(Request * request){
    return request->id;
}

int getRtime(Request * request){
    return request->time;
}

int getNCommands(Request * request){
    return request->argc;
}

void destroyRequest(Request * request){
    for(int i = 0;i < request->argc;i++){
        destroyCommand(request->commands[i]);
    }
    free(request->commands);
    request->commands = NULL;
    free(request);
    request = NULL;
}