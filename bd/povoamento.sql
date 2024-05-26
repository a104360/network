-- Exemplos para a tabela Pagamento
INSERT INTO Pagamento (estado, valor, metodo, data) VALUES 
(true, 150.00, 1, '2024-01-15'),
(false, 200.00, 2, '2024-02-20'),
(true, 300.50, 1, '2024-03-10'),
(false, 450.00, 3, '2024-04-05');

-- Exemplos para a tabela Despesa
INSERT INTO Despesa (bonus, alimentacao, alojamento, equipamento, outros) VALUES 
(100, 50, 150, 200, 100),
(150, 60, 180, 220, 120),
(120, 70, 160, 180, 110),
(200, 80, 200, 250, 150);

-- Exemplos para a tabela Localidade
INSERT INTO Localidade (nome) VALUES 
('Lisboa'),
('Porto'),
('Braga'),
('Faro'),
('Coimbra'),
('Madrid');

-- Exemplos para a tabela Agencia
INSERT INTO Agencia (email, numero, porta, localidade, codigoPostal) VALUES 
('braga@iap.pt', 253303030, 10, 3, 4719006);

-- Exemplos para a tabela Funcionario
INSERT INTO Funcionario (nome, salario, inicioContrato, fimContrato, departamento, posicao, dataNascimento, porta, localidade, codigoPostal, numero, email, agencia) VALUES 
('Aristides Piratininga', 2500.00, '2022-05-20', NULL, 'Direção', 'CEO', '1986-12-01', 13, 3, 4730301, 910934415, 'aristides.obi@investigacao.pt', 1),
('Laura Hipólito', 2500.00, '2022-05-25', NULL, 'Letigio', 'Advogada', '2000-04-25', 34, 6, 4730203, 664115266, 'hipolito@investigacao.pt', 1),
('João Freitas', 2450.00, '2022-06-14', NULL, 'Gestão', 'Gestor Sénior', '2003-11-15', 56, 3, 4715001, 967566234, 'joao.freitas@investigacao.pt', 1),
('Maria Costa', 700.00, '2024-01-15', NULL, 'Investigação', 'Agente júnior', '1999-03-30', 78, 3, 4720313, 913141250, 'maria.costa@investigacao.pt', 1),
('Marco Costa', 700.00, '2024-01-15', NULL, 'Investigação', 'Agente júnior', '2004-10-10', 3, 3, 4702712, 917771322, 'marco.tosta@investigacao.pt', 1);

-- Exemplos para a tabela Cliente
INSERT INTO Cliente (nome, registoCriminal, NIF, porta, localidade, codigoPostal, numero, email) VALUES 
('Carlos Almeida', 'Sem registo', 123456789, 5, 1, 1100, 987654321, 'carlos.almeida@cliente.pt'),
('Sofia Carvalho', 'Sem registo', 987654321, 15, 2, 4100, 876543210, 'sofia.carvalho@cliente.pt'),
('Rui Martins', 'Registo de pequeno delito', 112233445, 25, 3, 8000, 765432109, 'rui.martins@cliente.pt'),
('Beatriz Oliveira', 'Sem registo', 556677889, 35, 4, 3000, 654321098, 'beatriz.oliveira@cliente.pt');

-- Exemplos para a tabela Pedido
INSERT INTO Pedido (nivel, tipo, custo, relatorio, status, inicio, fim, idPagamento, idCliente, idAgencia, idDespesa) VALUES 
('Regional', 'Investigação', 1500.00, 'Relatório completo', 1, '2024-02-01', '2024-02-28', 1, 1, 1, 1),
('Regional', 'Monitorização', 1200.00, 'Relatório parcial', 0, '2024-03-01', '2024-03-20', 2, 2, 1, 2),
('Local', 'Investigação', 900.00, 'Relatório consultoria', 1, '2024-04-01', '2024-04-15', 3, 3, 1, 3),
('Nacional', 'Investigação', 2000.00, 'Relatório final', 1, '2024-05-01', '2024-05-30', 4, 4, 1, 4);


-- Exemplos para a tabela AgentesDoPedido
INSERT INTO AgentesDoPedido (idPedido, idAgente) VALUES 
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 4),
(4, 5);

-- Exemplos para a tabela Tecnica
INSERT INTO Tecnica (nome) VALUES 
('Vigilância'),
('Entrevista'),
('Análise de Dados'),
('Verificação de Alibis');

-- Exemplos para a tabela TecnicaDaInvestigacao
INSERT INTO TecnicaDaInvestigacao (pedido, tecnica) VALUES 
(1, 1),
(1, 2),
(1, 4),
(2, 2),
(3, 3),
(3, 1),
(4, 4);