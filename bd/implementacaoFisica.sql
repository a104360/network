DROP DATABASE IF EXISTS InvestigacaoAPortuguesa;


create database if not exists InvestigacaoAPortuguesa;

use InvestigacaoAPortuguesa;

create table if not exists Pagamento(
idPagamento int not null auto_increment,
estado boolean not null,
valor double not null,
metodo int not null,
data date,
primary key (idPagamento)
);

create table if not exists Despesa(
idDespesa int not null auto_increment,
bonus int not null,
alimentacao int not null,
alojamento int not null,
equipamento int not null,
outros int not null,
primary key (idDespesa)
);

create table if not exists Localidade(
	idLocalidade int not null auto_increment,
    nome varchar(45),
    primary key(idLocalidade)
);

create table if not exists Agencia(
	idAgencia int not null auto_increment,
    email varchar(45) not null,
    numero int not null,
    porta int not null,
    localidade int not null,
    codigoPostal int not null,
    primary key(idAgencia),
    foreign key (localidade) references Localidade(idLocalidade)
);

create table if not exists Funcionario(
	idFuncionario int not null auto_increment,
    nome varchar(45) not null,
    salario double not null,
    inicioContrato date not null,
    fimContrato date,
    departamento varchar(45) not null,
    posicao varchar(45) not null,
    dataNascimento date not null,
    porta int not null,
    localidade int not null,
    codigoPostal int not null,
    numero int not null,
    email varchar(45) not null,
    agencia int not null,
    primary key (idFuncionario),
    foreign key (localidade) references Localidade(idLocalidade),
    foreign key(agencia) references Agencia(idAgencia)
);

create table if not exists Cliente (
	idCliente int not null auto_increment,
    nome varchar(45) not null,
    registoCriminal text,
    NIF int not null,
    porta int not null,
    localidade int not null,
    codigoPostal int not null,
    numero int not null,
    email varchar(45),
    primary key (idCliente),
    foreign key (localidade) references Localidade(idLocalidade)
);

create table if not exists Pedido (
	idPedido int not null auto_increment,
    nivel varchar(45),
    tipo varchar(45),
    relatorio text not null,
    status int not null,
    inicio date not null,
	fim date,
    idPagamento int not null,
    idCliente int not null,
    idAgencia int not null,
    idDespesa int not null,
    primary key (idPedido),
    foreign key (idPagamento) references Pagamento(idPagamento),
    foreign key (idCliente) references Cliente(idCliente),
    foreign key (idAgencia) references Agencia(idAgencia),
    foreign key (idDespesa) references Despesa(idDespesa)
);

create table if not exists AgentesDoPedido (
	idPedido int not null,
    idAgente int not null,
    primary key (idPedido,idAgente),
    foreign key (idPedido) references Pedido(idPedido),
    foreign key (idAgente) references Funcionario(idFuncionario)
);

create table if not exists Tecnica (
	idTecnica int not null auto_increment,
    nome varchar(45) not null,
    primary key (idTecnica)
);

create table if not exists TecnicaDoPedido (
	pedido int not null,
	tecnica int not null,
    primary key (tecnica,pedido),
    foreign key (pedido) references Pedido(idPedido),
    foreign key (tecnica) references Tecnica(idTecnica)
);

-- Funcao para calcular as despesas totais de um pedido
DELIMITER $$
CREATE FUNCTION calcularDespesaTotal(pedidoId INT) 
	RETURNS DOUBLE
	DETERMINISTIC
	BEGIN
		DECLARE total DOUBLE;

		SELECT (d.bonus + d.alimentacao + d.alojamento + d.equipamento + d.outros) INTO total
		FROM Pedido p
		INNER JOIN Despesa d ON p.idDespesa = d.idDespesa
		WHERE p.idPedido = pedidoId;

		RETURN total;
	END $$




-- Querie1 agentes que nao estao a associados a pedidos
delimiter $$
CREATE PROCEDURE agentesLivres()
	BEGIN
		SELECT idFuncionario AS Agentes FROM Funcionario 
			WHERE Funcionario.departamento LIKE 'Inv%'
			AND Funcionario.idFuncionario NOT IN (
				SELECT idAgente FROM AgentesDoPedido 
				JOIN Pedido ON AgentesDoPedido.idPedido = Pedido.idPedido 
				WHERE Pedido.status = 0
			);
END $$

    
    
    
-- Querie2 pedidos que estao pendentes
delimiter $$
CREATE PROCEDURE pedidosPendentes()
	BEGIN
		SELECT idPedido FROM Pedido
		WHERE Pedido.status = 0;
	END $$
    
    
    
    
-- Querie3 ver quanto se gastou em equipamentos num pedido
delimiter $$
CREATE PROCEDURE gastoEquipamentosPedido(IN idP INT)
	BEGIN
		SELECT equipamento AS gastoEquipamento FROM Despesa 
        JOIN Pedido ON Pedido.idPedido = idP
			WHERE Despesa.idDespesa = Pedido.idDespesa;
    END $$




-- Querie4 todos os pedidos associados a um cliente
delimiter $$
CREATE PROCEDURE pedidosCliente(IN idC INT)
	BEGIN
		SELECT Pedido.idPedido, Pedido.nivel, Pedido.relatorio, Pedido.status, Pedido.inicio, Pedido.fim, Pagamento.estado AS estado_Pagamento, Cliente.nome AS nome_Cliente, Agencia.codigoPostal AS codigoPostal_Agencia FROM Pedido
			INNER JOIN Pagamento ON Pagamento.idPagamento = Pedido.idPagamento INNER JOIN Cliente ON Cliente.idCliente = Pedido.idCliente INNER JOIN Agencia ON Agencia.idAgencia = Pedido.idAgencia
			WHERE Pedido.idCliente = idC;
	END $$




-- TRIGGER Verificar se a data de inicio de um pedido vem antes da data de fim
DELIMITER $$
CREATE TRIGGER verificaDataPedido
	BEFORE UPDATE ON Pedido
	FOR EACH ROW
	BEGIN
		IF NEW.inicio > NEW.fim THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'A data de início é posterior à data de fim'; -- 45000 Erro defenido pelo usuário
		END IF;
	END $$



-- PROCEDURE
DELIMITER $$
CREATE PROCEDURE atualizarStatusPedido(IN pedidoId INT, IN novoStatus INT, IN dataFim DATE)
	BEGIN
		START TRANSACTION;
		
        -- Atualiza o status do pedido e a data de fim
		UPDATE Pedido
		SET status = novoStatus, fim = dataFim
		WHERE idPedido = pedidoId;

		-- Verifica se o pedido foi atualizado
		IF ROW_COUNT() = 0 THEN
			ROLLBACK;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Pedido não encontrado ou atualização falhou';
		ELSE
			COMMIT;
		END IF;
	END $$




-- VIEW para ver os detalhes de um pedido
CREATE VIEW DetalhesPedido AS
	SELECT 
		p.idPedido, p.nivel, p.tipo, p.relatorio, p.status, p.inicio, p.fim,
		pg.estado AS estado_pagamento, c.nome AS nome_cliente, a.email AS email_agencia,
        calcularDespesaTotal(p.idPedido) AS despesa
		FROM Pedido p
		INNER JOIN Pagamento pg ON p.idPagamento = pg.idPagamento
		INNER JOIN Cliente c ON p.idCliente = c.idCliente
		INNER JOIN Agencia a ON p.idAgencia = a.idAgencia
		INNER JOIN Despesa d ON p.idDespesa = d.idDespesa;



-- VIEW Ids dos pedidos com os agentes associados
CREATE VIEW AgentesPorPedido AS
	SELECT AgentesDoPedido.idPedido, Funcionario.nome AS nome_agente
	FROM AgentesDoPedido
	INNER JOIN Funcionario ON AgentesDoPedido.idAgente = Funcionario.idFuncionario;




-- VIEW Todos os funcionarios ordenados por id agencia
CREATE VIEW FuncionariosPorAgencia AS
	SELECT * FROM Funcionario
		ORDER BY agencia;