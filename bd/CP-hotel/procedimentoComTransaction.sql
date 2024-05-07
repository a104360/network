delimiter $
create procedure reservarQuarto(in cliente int,in checkin date,in checkout date)
begin
declare idquarto int;
start transaction;
select num into idquarto from quarto where estado = 0 limit 1;
if idquarto is not null then
	insert into reserva (checkin,checkout,num) values (checkin,checkout,idquarto);
    commit;
    select 'Reserva efetuada com sucesso';
else 
	rollback;
    select 'Não existem quartos disponiveis';
end if;

end  $

call reservarQuarto(2,"2000-12-12","2021-12-12");