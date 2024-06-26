

-- Insert sample data into morada table
INSERT INTO morada (rua, localidade, codigoPostal) VALUES
('Rua da Amizade', 'Lisbon', '1000-001'),
('Avenida Central', 'Porto', '4000-002'),
('Praça da Liberdade', 'Faro', '8000-003');

-- Insert sample data into cliente table
INSERT INTO cliente (nome, idade, dataNascimento, morada) VALUES
('John Doe', 30, '1994-05-15', 1),
('Jane Smith', 25, '1999-10-20', 2),
('Alice Johnson', 40, '1984-03-10', 3);

-- Insert sample data into hotel table
INSERT INTO hotel (nome, estrelas) VALUES
('Hotel A', 4),
('Hotel B', 3),
('Hotel C', 5);

-- Insert sample data into contacto table
INSERT INTO contacto (tipo, valor, idCliente, idHotel) VALUES
('Email', 'john@example.com', 1, 1),
('Telefone', '123-456-789', 2, 2),
('Email', 'alice@example.com', 3, 3);

-- Insert sample data into quarto table
INSERT INTO quarto (npax, tipo, estado, idHotel) VALUES
('Single', 'Standard', 1, 1),
('Double', 'Suite', 1, 2),
('Single', 'Standard', 1, 3);

INSERT INTO funcionario (nome , funcao , idHotel) values
('Jipow' , 'Rececionista' , '1'), 
('Antonio','Limpeza','1'),
('Cassandra','Hacker','2'),
('Josefino','Servidores','2'),
('Aristides','Faztudo','3');


INSERT INTO reserva (quarto,cliente,valor) values 
(1,1,1000),
(1,2,2000),
(2,3,5000);

select * from quarto;
-- update quarto set estado = 1 where idHotel = 3;

select * from cliente;

update reserva set dataReserva = '2002-12-2' where idReserva = 3;

update quarto set lucro = 0 where num = 3;

-- select cliente.nome,cliente.morada,contacto.valor from cliente inner join contacto on cliente.morada=contacto.idCliente
