package tabelas;

public class AgenciaDoPedido{
    private int idPedido;
    private int idAgente;

    public AgenciaDoPedido(int idPedido, int idAgente) {
        this.idPedido = idPedido;
        this.idAgente = idAgente;
    }

    @Override
    public String toString() {
        return "AgenciaDoPedido [idPedido=" + idPedido + ", idAgente=" + idAgente + "]";
    }
    

}