package tabelas; 

import java.sql.Date;

public class Pedido{
    private int id;
    private String nivel;
    private String tipo;
    private double custo;
    private String relatorio;
    private int status;
    private Date inicio;
    private Date fim;
    private int pagamento;
    private int cliente;
    private int agencia;


    public Pedido(int id, String nivel, String tipo, double custo, String relatorio, int status, Date inicio, Date fim,
            int pagamento, int cliente, int agencia) {
        this.id = id;
        this.nivel = nivel;
        this.tipo = tipo;
        this.custo = custo;
        this.relatorio = relatorio;
        this.status = status;
        this.inicio = inicio;
        this.fim = fim;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.agencia = agencia;
    }


    @Override
    public String toString() {
        return "Pedido [id=" + id + ", nivel=" + nivel + ", tipo=" + tipo + ", custo=" + custo + ", relatorio="
                + relatorio + ", status=" + status + ", inicio=" + inicio + ", fim=" + fim + ", pagamento=" + pagamento
                + ", cliente=" + cliente + ", agencia=" + agencia + "]";
    }

}