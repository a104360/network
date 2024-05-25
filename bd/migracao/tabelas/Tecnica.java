package tabelas;

public class Tecnica {
    private int id;
    private int pedido;

    public Tecnica(int id, int pedido) {
        this.id = id;
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Tecnica [id=" + id + ", pedido=" + pedido + "]";
    }

}