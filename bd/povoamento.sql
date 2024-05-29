-- Exemplos para a tabela Pagamento
INSERT INTO Pagamento (estado, valor, metodo, data) VALUES 
(true, 1500.00, 1, '2024-05-01'),
(false, 550.00, 2, null), 
(true, 430.00, 2, '2024-05-02'),
(true, 600.00, 1, '2024-05-03'),
(false, 700.00, 3, null),
(true, 2000.00, 2, '2024-07-01'),
(true, 400.00, 1, '2024-08-20'),
(false, 350.00, 2, null), 
(true, 450.00, 2, '2024-10-25'),
(true, 550.00, 1, '2024-11-02'),
(true, 500.00, 3, '2024-12-05'),
(true, 750.00, 1, '2025-01-03');

-- Exemplos para a tabela Despesa
INSERT INTO Despesa (bonus, alimentacao, alojamento, equipamento, outros) VALUES 
(100, 50, 100, 50, 50),
(0, 100, 0, 0, 0),
(100, 50, 100, 50, 0),
(200, 50, 100, 100, 50),
(0, 100, 0, 0, 0),
(200, 50, 100, 50, 0),
(100, 50, 100, 50, 0),
(0, 100, 0, 0, 0),
(200, 50, 100, 50, 0),
(250, 100, 100, 50, 0),
(0, 100, 0, 0, 0),
(250, 100, 100, 50, 0);


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
('braga@iap.pt', 253303030, 10, 3, 4719006),
('lisboa@iap.pt', 253236421, 15, 1, 1346007);

-- Exemplos para a tabela Funcionario
INSERT INTO Funcionario (nome, salario, inicioContrato, fimContrato, departamento, posicao, dataNascimento, porta, localidade, codigoPostal, numero, email, agencia) VALUES 
('Aristides Piratininga', 2500.00, '2022-05-20', NULL, 'Administração', 'CEO', '1986-12-01', 13, 3, 4730301, 910934415, 'aristides.obi@investigacao.pt', 1),
('Laura Hipólito', 2500.00, '2022-05-25', NULL, 'Equipa jurídica', 'Advogada', '2000-04-25', 34, 6, 4730203, 664115266, 'hipolito@investigacao.pt', 1),
('João Freitas', 2450.00, '2022-06-14', NULL, 'Finanças', 'Gestor Sénior', '2003-11-15', 56, 2, 4715001, 967566234, 'joao.freitas@investigacao.pt', 1),
('Maria Costa', 700.00, '2024-01-15', NULL, 'Investigação', 'Agente sénior', '1999-03-30', 78, 4, 4720313, 913141250, 'maria.costa@investigacao.pt', 1),
('Marco Costa', 700.00, '2024-01-15', NULL, 'Investigação', 'Agente júnior', '2004-10-10', 3, 5, 4702712, 917771322, 'marco.tosta@investigacao.pt', 1),
('Ana Silva', 1500.00, '2023-01-10', '2024-01-10', 'Finanças', 'Contabilista', '1980-07-22', 45, 1, 4725301, 912345678, 'ana.silva@investigacao.pt', 2),
('Rui Dias', 1300.00, '2023-02-01', '2024-02-01', 'Finanças', 'Assistente Financeiro', '1992-02-13', 50, 2, 4720302, 912345679, 'rui.dias@investigacao.pt', 1),
('Inês Marques', 900.00, '2023-03-01', NULL, 'Investigação', 'Agente', '1995-05-10', 21, 3, 4710303, 912345680, 'ines.marques@investigacao.pt', 2),
('Pedro Almeida', 950.00, '2023-04-15', '2024-04-15', 'Investigação', 'Agente', '1998-08-22', 15, 1, 4705311, 912345681, 'pedro.almeida@investigacao.pt', 2),
('Sara Fernandes', 1200.00, '2023-06-10', NULL, 'Equipa jurídica', 'Advogada júnior', '1997-12-05', 30, 1, 4720402, 912345682, 'sara.fernandes@investigacao.pt', 2),
('Luís Gomes', 1300.00, '2023-07-01', '2024-07-01', 'Finanças', 'Analista', '1990-10-14', 25, 4, 4710304, 912345683, 'luis.gomes@investigacao.pt', 2),
('Beatriz Silva', 1400.00, '2023-08-05', NULL, 'Administração', 'Assistente', '1985-11-20', 18, 5, 4725302, 912345684, 'beatriz.silva@investigacao.pt', 2);

-- Exemplos para a tabela Cliente
INSERT INTO Cliente (nome, registoCriminal, NIF, porta, localidade, codigoPostal, numero, email) VALUES 
('Carlos Almeida', 'Sem registo', 123456789, 5, 1, 1100, 987654321, 'carlos.almeida@cliente.pt'),
('Sofia Carvalho', 'Sem registo', 987654321, 15, 2, 4100, 876543210, 'sofia.carvalho@cliente.pt'),
('Rui Martins', 'Registo de pequeno delito', 112233445, 25, 3, 8000, 765432109, 'rui.martins@cliente.pt'),
('Beatriz Oliveira', 'Sem registo', 556677889, 35, 4, 3000, 654321098, 'beatriz.oliveira@cliente.pt'),
('João Silva', 'Sem registo', 998877665, 45, 5, 2000, 543210987, 'joao.silva@cliente.pt'),
('Marta Santos', 'Sem registo', 334455667, 55, 6, 9000, 432109876, 'marta.santos@cliente.pt');


-- Exemplos para a tabela Pedido
INSERT INTO Pedido (nivel, tipo, relatorio, status, inicio, fim, idPagamento, idCliente, idAgencia, idDespesa) VALUES 
('Regional', 'Investigação', 'Relatório completo: ...', 1, '2024-02-01', '2024-02-28', 1, 1, 1, 1),
('Local', 'Monitorização', 'Relatório parcial: ...', 0, '2024-03-01', null, 2, 2, 2, 2),
('Local', 'Investigação', 'Relatório consultoria: ...', 1, '2024-04-01', '2024-04-15', 3, 3, 2, 3),
('Nacional', 'Investigação', 'Relatório final: ...', 1, '2024-05-01', '2024-05-30', 4, 4, 1, 4),
('Local', 'Menores', 'Relatório inicial: ...', 0, '2024-06-01', null, 5, 5, 2, 5),
('Internacional', 'Infidelidade', 'Relatório detalhado: ...', 1, '2024-07-01', '2024-07-31', 6, 6, 1, 6),
('Local', 'Investigação', 'Relatório de progresso: ...', 1, '2024-08-01', '2024-08-20', 7, 4, 1, 7),
('Local', 'Monitorização', 'Relatório sumário: ...', 0, '2024-09-01', null, 8, 2, 1, 8),
('Nacional', 'Menores', 'Relatório final: ...', 1, '2024-10-01', '2024-10-25', 9, 5, 2, 9),
('Governamental', 'Infidelidade', 'Relatório preliminar: ...', 1, '2024-11-01', '2024-11-30', 10, 3, 2, 10),
('Local', 'Monitorização', 'Relatório interino: ...', 1, '2024-12-01', '2024-12-05', 11, 4, 1, 11),
('Local', 'Infidelidade', 'Relatório de conclusão: ...', 1, '2024-12-15', '2024-12-31', 12, 1, 1, 12);

INSERT INTO AgentesDoPedido (idPedido, idAgente) VALUES 
(1, 4),
(1, 5),
(2, 9),
(3, 8),
(4, 4),
(4, 5),
(5, 8),
(6, 4),
(7, 5),
(8, 5),
(9, 9),
(10, 9),
(11, 4),
(12, 4);


-- Exemplos para a tabela Tecnica
INSERT INTO Tecnica (nome) VALUES 
('Vigilância'),
('Entrevista'),
('Análise de Dados'),
('Verificação de Alibis');

-- Exemplos para a tabela TecnicaDoPedido
INSERT INTO TecnicaDoPedido (pedido, tecnica) VALUES 
(1, 1),
(1, 2),
(1, 4),
(2, 2),
(3, 3),
(3, 1),
(4, 4),
(5, 1),
(5, 3),
(6, 2),
(7, 1),
(7, 4),
(8, 2),
(9, 3),
(9, 4),
(10, 1),
(11, 2),
(11, 3),
(12, 4),
(12, 1);