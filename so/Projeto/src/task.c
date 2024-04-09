#include <unistd.h>

typedef struct task {
	short id;
	char ** request; // Pedidos a executar
	int time; // Duracao em milisegundos
} Task;


Task * newTask(short id, char ** request,int time){
	
}
