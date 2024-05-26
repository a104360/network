package tabelas;

public class AgentesDoPedido extends Entidade{
    private int idPedido;
    private int idAgente;

    public AgentesDoPedido(int idPedido, int idAgente) {
        this.idPedido = idPedido;
        this.idAgente = idAgente;
    }
    public AgentesDoPedido() {
        this.idPedido = 0;
        this.idAgente = 0;
    }
    
    @Override
    public String toString() {
        return "(" + idPedido + "," + idAgente + ")";
    }
    
    public void load(String line){
        String[] values = line.split(","); 
        this.idPedido = Integer.parseInt(values[0]);
        this.idAgente = Integer.parseInt(values[1]);
    }

    public Entidade createInstance(){
        return new AgentesDoPedido();
    }
}