-- Insert into Pagamento
INSERT INTO Pagamento (estado, valor, metodo, data) VALUES 
(true, 150.00, 1, '2024-01-15'),
(false, 200.00, 2, '2024-02-20'),
(true, 300.50, 1, '2024-03-10'),
(false, 450.00, 3, '2024-04-05');

-- Insert into Localidade
INSERT INTO Localidade (nome) VALUES 
('Lisboa'),
('Porto'),
('Braga'),
('Faro'),
('Coimbra'),
('Madrid');

-- Insert into Agencia
INSERT INTO Agencia (email, numero, porta, localidade, codigoPostal) VALUES 
('braga@iap.pt', 253303030, 10, 3, 4719006);

-- Insert into Funcionario
INSERT INTO Funcionario (nome, salario, inicioContrato, fimContrato, departamento, posicao, dataNascimento, porta, localidade, codigoPostal, numero, email, agencia) VALUES 
('Aristides Piratininga', 2500.00, '2022-05-20', NULL, 'Direção', 'CEO', '1986-12-01', 13, 3, 4730301, 910934415, 'aristides.obi@investigacao.pt', 1),
('Laura Hipólito', 2500.00, '2022-05-25', NULL, 'Letigio', 'Advogada', '2000-04-25', 34, 6, 4730203, 664115266, 'hipolito@investigacao.pt', 1),
('João Freitas', 2450.00, '2022-06-14', NULL, 'Gestão', 'Gestor Sénior', '2003-11-15', 56, 3, 4715001, 967566234, 'joao.freitas@investigacao.pt', 1),
('Maria Costa', 700.00, '2024-01-15', NULL, 'Investigação', 'Agente júnior', '1999-03-30', 78, 3, 4720313, 913141250, 'maria.costa@investigacao.pt', 1),
('Marco Costa', 700.00, '2024-01-15', NULL, 'Investigação', 'Agente júnior', '2004-10-10', 3, 3, 4702712, 917771322, 'marco.tosta@investigacao.pt', 1);


-- Insert into Cliente
select * from Cliente;
INSERT INTO Cliente (nome, registoCriminal, NIF, porta, localidade, codigoPostal, numero, email) VALUES 
('Carlos Almeida', 'Sem registo', 123456789, 5, 1, 1100, 987654321, 'carlos.almeida@cliente.pt'),
('Sofia Carvalho', 'Sem registo', 987654321, 15, 2, 4100, 876543210, 'sofia.carvalho@cliente.pt'),
('Rui Martins', 'Registo de pequeno delito', 112233445, 25, 3, 8000, 765432109, 'rui.martins@cliente.pt'),
('Beatriz Oliveira', 'Sem registo', 556677889, 35, 4, 3000, 654321098, 'beatriz.oliveira@cliente.pt');

-- Insert into Pedido
INSERT INTO Pedido (nivel, tipo, custo, relatorio, status, inicio, fim, idPagamento, idCliente, idAgencia) VALUES 
('Regional', 'Investigação', 1500.00, 'Relatório completo', 1, '2024-02-01', '2024-02-28', 1, 1, 1),
('Regional', 'Monitorização', 1200.00, 'Relatório parcial', 0, '2024-03-01', '2024-03-20', 2, 2, 1),
('Local', 'Investigação', 900.00, 'Relatório consultoria', 1, '2024-04-01', '2024-04-15', 3, 3, 1),
('Nacional', 'Investigação', 2000.00, 'Relatório final', 1, '2024-05-01', '2024-05-30', 4, 4, 1);

-- Insert into AgentesDoPedido
INSERT INTO AgentesDoPedido (idPedido, idAgente) VALUES 
(8, 14),
(8, 15),
(6, 15),
(7, 15);

select * from Pedido;

-- Insert into Tecnica
INSERT INTO Tecnica (idPedido) VALUES 
(5),
(7),
(8),
(8);
