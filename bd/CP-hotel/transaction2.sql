delimiter $
create procedure atualizaReserva(in idReserva int,in novocheckin date,in novocheckout date,in tipoQuarto varchar(45))
begin 
declare oldcheckin date;
declare oldcheckout date;
start transaction;
select checkin,checkout,oldcheckin,oldcheckout from reserva where idReserva = idReserva;
if(oldcheckin <> novocheckin,oldcheckout <> novocheckout) then
	update reserva 
    set checkin = novocheckin,
    checkout = novocheckout
    where id = reserva;
    commit;
    select 'Reserva atualizada';
else
	rollback;
    select 'As datas são iguais';
end if;
end$