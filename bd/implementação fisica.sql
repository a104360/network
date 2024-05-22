create database if not exists InvestigacaoAPortuguesa;

use InvestigacaoAPortuguesa;

create table if not exists Pagamento(
idPagamento int not null auto_increment,
estado boolean not null,
valor double not null,
metodo int not null,
data date not null,
primary key (idPagamento)
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
    custo double not null,
    relatorio text not null,
    status int not null,
    inicio date not null,
	fim date,
    idPagamento int not null,
    idCliente int not null,
    idAgencia int not null,
    primary key (idPedido),
    foreign key (idPagamento) references Pagamento(idPagamento),
    foreign key (idCliente) references Cliente(idCliente),
    foreign key (idAgencia) references Agencia(idAgencia)
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
    idPedido int not null,
    primary key (idTecnica),
    foreign key (idPedido) references Pedido(idPedido)
);