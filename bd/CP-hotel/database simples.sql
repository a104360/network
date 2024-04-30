create database if not exists hotel;

use hotel;

create table if not exists morada(
idMorada int not null auto_increment,
rua varchar(69) not null,
localidade varchar(34) not null,
codigoPostal varchar(12) not null,
primary key (idMorada)
);

create table if not exists cliente(
contribuinte int not null auto_increment,
nome varchar(69),
idade int,
dataNascimento date,
morada int not null,
primary key (contribuinte),
foreign key (morada) references morada(idMorada)
);

create table if not exists hotel(
identificador int not null auto_increment,
nome varchar(69) not null,
estrelas int not null,
primary key (identificador)
);

create table if not exists contacto(
idContacto int not null auto_increment,
tipo varchar(69) not null,
valor varchar(45) not null,
idCliente int not null,
idHotel int,
primary key (idContacto),
foreign key (idCliente) references cliente(contribuinte),
foreign key (idHotel) references hotel(identificador)
);

create table if not exists quarto(
num int not null auto_increment,
npax varchar(69),
tipo varchar(45),
estado binary not null,
idHotel int not null,
primary key (num),
foreign key (idHotel) references hotel(identificador)
);

create table if not exists funcionario(
idFuncionario int not null auto_increment,
nome varchar(70),
funcao varchar(45),
idHotel int not null,
primary key (idFuncionario),
foreign key (idHotel) references hotel(identificador)
);

create table if not exists reserva(
idReserva int not null auto_increment,
quarto int not null,
cliente int not null,
valor int not null,
primary key (idReserva),
foreign key (quarto) references quarto(num),
foreign key (cliente) references cliente(contribuinte)
);

alter table reserva 
	add dataReserva date;
    
    
alter table quarto 
	add lucro int;

select * from funcionario;