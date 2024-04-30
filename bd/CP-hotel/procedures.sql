delimiter $$
create procedure getfuncionariosporfuncao(in funcao2 varchar(45))
begin
	select * from funcionario where funcao = funcao2;
end;$$

call getfuncionariosporfuncao('Rececionista');

Delimiter $$
create procedure getReservasAno(in r binary)
	begin
		select count(*) from quarto where estado = r;
	end $$

call getReservasAno(1);


	
delimiter %% 
create function idade (data date)
returns int
deterministic
begin
return timestampdiff(year,data,curdate());
end%%

delimiter %%
create procedure getIdade(in pessoa int)
begin
	update cliente set idade = idade(dataNascimento) where contribuinte = pessoa;
end;%%


call getIdade(3);


delimiter $$ 
create function getNovoLucro(nr int,valor int)
returns int
deterministic
begin
	declare store int;
	select lucro into store from quarto where num = nr;
    return store + valor;
end$$

delimiter $$
create procedure updateLucroQuarto(in nr int, in nLucro int)
begin 
	update quarto set lucro = getNovoLucro(nr,nLucro) where quarto.num = nr;
end$$

call updateLucroQuarto(1,20);
select * from quarto;
delimiter ;