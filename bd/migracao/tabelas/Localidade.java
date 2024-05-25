package tabelas;

public class Localidade{
    private int id;
    private String nome;

    
    public Localidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Localidade [id=" + id + ", nome=" + nome + "]";
    }

    
}