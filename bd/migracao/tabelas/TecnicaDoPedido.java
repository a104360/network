package tabelas;

public class TecnicaDoPedido extends Entidade {
    private int idTecnica;
    private int idPedido;
    
    public TecnicaDoPedido(){
        this.idTecnica = 0;
        this.idPedido = 0;
    }

    @Override
    public String toString() {
        return "TecnicaDoPedido [idTecnica=" + idTecnica + ", idPedido=" + idPedido + "]";
    }

    public void load(String line){
        String[] values = line.split(",");
        this.idTecnica = Integer.parseInt(values[0]);
        this.idPedido = Integer.parseInt(values[1]);
    }


    public Entidade createInstance(){
        return new TecnicaDoPedido();
    }


}
