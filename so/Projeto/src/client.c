#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <unistd.h>
#include "../include/parser.h"
#include "../include/request.h"

extern int clientID;

int main(int argc,char ** argv){
	switch (argc)
	{
	case EXECUTE:
		Request * request = NULL;
		if(strncasecmp(argv[3],"-u") == 0){
			char * command = parseUniqueCommand(argv[4]);
			request = createRequest(clientID++,stringToInt(argv[2]),&command,1);
			// sendRequest;
			destroyRequest(request);
			return 0;
		}
		else{
			char ** commands = parseMultipleCommands(argv[4]);
			request = createRequest(clientID++,stringToInt(argv[2]),commands,countCommands(argv[4]));
			// sendRequest
			destroyRequest(request);
			return 0;
		}
		break;
	case STATUS:
		break;
	default:
		break;
	}
	return 0;
}
