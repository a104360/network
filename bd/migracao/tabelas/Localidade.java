package tabelas;

public class Localidade extends Entidade{
    private String nome;

    public Localidade() {
        this.nome = null;
    }
    
    public Localidade(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "('" + nome + "')";
    }

    public void load(String line){
        this.nome = line;
    }    

    public Entidade createInstance(){
        return new Localidade();
    }
}