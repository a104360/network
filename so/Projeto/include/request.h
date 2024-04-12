#include "../include/command.h"

/// @brief Struct para definir um pedido do cliente
typedef struct request Request;

/// @brief Construtor de pedido
/// @param  id id do pedido
/// @param  time referencia temporal para a resposta do servidor
/// @param  argv array de argumentos passados ao cliente pelo utilizador
/// @param  argc numero de elementos do array de argumentos
/// @return Pointer para o request criado
Request *createRequest(int, int,Command **, int);

/// @brief Getter id pedido
/// @param  request Pointer para o request
/// @return id do pedido
int getRid(Request *);

/// @brief Getter tempo de referencia do pedido
/// @param  request Pointer para o request
/// @return Tempo de referencia em milisegundos
int getRtime(Request *);

/// @brief Getter dos comandos
/// @param  request Pointer para o request
/// @return Array com os comandos a executar
Command ** getCommands(Request *);

/// @brief Getter numero de comandos
/// @param  request Pointer para o request
/// @return Número de comandos a executar no pedido
int getNCommands(Request *);

/// @brief Função de desalocação de memória para o request
/// @param  request Pointer para o request
void destroyRequest(Request *);
