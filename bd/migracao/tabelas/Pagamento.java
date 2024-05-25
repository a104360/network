package tabelas;

import java.sql.Date;

public class Pagamento{
    private int id;
    private boolean estado;
    private double valor;
    private int metodo;
    private Date data;

    
    public Pagamento(int id, boolean estado, double valor, int metodo, Date data) {
        this.id = id;
        this.estado = estado;
        this.valor = valor;
        this.metodo = metodo;
        this.data = data;
    }


    @Override
    public String toString() {
        return "Pagamento [id=" + id + ", estado=" + estado + ", valor=" + valor + ", metodo=" + metodo + ", data="
                + data + "]";
    }

    

}