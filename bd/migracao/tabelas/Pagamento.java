package tabelas;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pagamento extends Entidade{
    private boolean estado;
    private double valor;
    private int metodo;
    private Date data;

    public Pagamento() {
        this.estado = false;
        this.valor = 0;
        this.metodo = 0;
        this.data = null;

    }
    
    public Pagamento(boolean estado, double valor, int metodo, Date data) {
        this.estado = estado;
        this.valor = valor;
        this.metodo = metodo;
        this.data = data;
    }
    
    
    @Override
    public String toString() {
        return "Pagamento [estado=" + estado + ", valor=" + valor + ", metodo=" + metodo + ", data="
        + data + "]";
    }
    
    public void load(String line){
        String[] values = line.split(",");
        this.estado = Boolean.parseBoolean(values[0]);
        this.valor = Double.parseDouble(values[1]);
        this.metodo = Integer.parseInt(values[2]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.data = Date.valueOf(LocalDate.parse(values[3],formatter));
    }    
    public Entidade createInstance(){
        return new Pagamento();
    }
}