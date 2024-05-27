package tabelas; 

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido extends Entidade{
    private String nivel;
    private String tipo;
    private String relatorio;
    private int status;
    private Date inicio;
    private Date fim;
    private int pagamento;
    private int cliente;
    private int agencia;
    private int despesa;


    public Pedido(String nivel, String tipo, String relatorio, int status, Date inicio, Date fim,
            int pagamento, int cliente, int agencia,int despesa) {
        this.nivel = nivel;
        this.tipo = tipo;
        this.relatorio = relatorio;
        this.status = status;
        this.inicio = inicio;
        this.fim = fim;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.agencia = agencia;
        this.despesa = despesa;
    }
    public Pedido() {
        this.nivel = null;
        this.tipo = null;
        this.relatorio = null;
        this.status = 0;
        this.inicio = null;
        this.fim = null;
        this.pagamento = 0;
        this.cliente = 0;
        this.agencia = 0;
        this.despesa = 0;
    }
    
    
    @Override
    public String toString() {
        String answer = "(";

        if(this.nivel == null){
            answer += "NULL,";
        } else {
            answer += "'" + this.nivel + "',";
        }

        if(this.tipo == null){
            answer += "NULL,";
        } else {
            answer += "'" + this.tipo + "'";
        }
        
        answer += ",'"+ relatorio + "'," + status + ",'" + inicio + "',";

        if(this.fim == null){
            answer += "NULL,";
        } else {
            answer += "'" + this.fim + "'";
        }

        answer += "," + pagamento + "," + cliente + "," + agencia + "," + this.despesa + ")";
        return answer; 
    }
    
    public void load(String line){
        String[] values = line.split(",");
        this.nivel = values[0];
        this.tipo = values[1];
        this.relatorio = values[2];
        this.status = Integer.parseInt(values[3]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.inicio = Date.valueOf(LocalDate.parse(values[4],formatter));
        this.fim = Date.valueOf(LocalDate.parse(values[5],formatter));
        this.pagamento = Integer.parseInt(values[6]);
        this.cliente = Integer.parseInt(values[7]);
        this.agencia = Integer.parseInt(values[8]);
        this.despesa = Integer.parseInt(values[9]);
    }

    public Entidade createInstance(){
        return new Pedido();
    }
}