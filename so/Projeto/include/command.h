
/// @brief Struct para armazenar o comando
/// @note Nome do comando; Argumentos; numero de argumentos 
typedef struct command Command;

/// @brief Criação de um comando
/// @param  name Nome do comando
/// @param  args Argumentos do comando
/// @param  argc Número de argumentos do comando
/// @return Pointer para o comando criado
Command * createCommand(char *,char **,int);

/// @brief Função de cópia de um comando
/// @param  command Pointer para o comando a ser copiado
/// @return Pointer para comando cópia
Command * copyCommand(Command *);

/// @brief Getter para o nome do comando
/// @param  command Pointer para o comando desejado
/// @return String com nome do comando
char * getCommandName(Command *);

/// @brief Setter para o nome do comando
/// @param  command Pointer para o comando
/// @param  name Novo nome para o comando
void setCommandName(Command *,char *);

/// @brief Getter para os argumentos do comando
/// @param  command Pointer para o comando
/// @return Array de argumentos do comando
char ** getCommandArgs(Command *);

/// @brief Setter para os argumentos do comando
/// @param  command Pointer para o comando
/// @param  args Novo array de argumentos do comando 
/// @param  argc Novo número de argumentos do comando
void setCommmandArgs(Command *,char **,int);

/// @brief Getter para o número de argumentos do comando
/// @param  command Pointer para o comando
/// @return Número de argumentos do comando
int getCommandNArgs(Command*);


/// @brief Função de destruição do comando
/// @param  command Pointer para o comando
void destroyCommand(Command *);
