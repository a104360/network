#include <stdlib.h>
#include <string.h>
#include "../include/command.h"
#include "../include/request.h"

struct command {
	char * name;
	char ** args;
	int argc;
};

static void copyArgs(Command * command,char ** args,int argc){
	for(int i = 0; i < argc; i++){
		command->args[i] = strdup(args[i]);
	}
}

Command * createCommand(char * name,char ** args, int argc){
	Command * command = malloc(sizeof(struct command));
	command->name = strdup(name);
	command->args = malloc(sizeof(char *) * argc); 
	copyArgs(command,args,argc);
	command->argc = argc;
	return command;
}

Command * copyCommand(Command * command){
	Command * copy = malloc(sizeof(struct command));
	copy->name = getCommandName(command);
    copy->args = getCommandArgs(command);
    copy->argc = getCommandNArgs(command);
	return copy;
}

char * getCommandName(Command * command){
	return strdup(command->name);
}

void setCommandName(Command * command,char * name){
	free(command->name);
	command->name = NULL;
	command->name = strdup(name);
}

char ** getCommandArgs(Command * command){
	char ** args = malloc(sizeof(char *) * command->argc);
	for(int i = 0;i < command->argc;i++){
		args[i] = strdup(command->args[i]);
	}
	return args;
}

void setCommmandArgs(Command * command,char ** args,int argc){
	for(int i = 0;i < command->argc;i++){
		free(command->args[i]);
		command->args[i] = NULL;
	}
	free(command->args);
	command->args = NULL;
	command->args = malloc(sizeof(char *) * argc);
	copyArgs(command,args,argc);
}

int getCommandNArgs(Command * command){
	return command->argc;
}

void destroyCommand(Command * command){
	for(int i = 0;i < command->argc;i++){
		free(command->args[i]);
		command->args[i] = NULL;
	}
	free(command->args);
	command->args = NULL;
	free(command->name);
	command->name = NULL;
	free(command);
	command = NULL;
}
