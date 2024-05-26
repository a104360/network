package tabelas;

public class Tecnica extends Entidade{
    private String nome;

    public Tecnica(){
        this.nome = null;
    }

    public Tecnica(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "Tecnica [nome=" + this.nome+ "]";
    }
    
    public void load(String line){
        this.nome = line;
    }

    public Entidade createInstance(){
        return new Tecnica();
    }

}