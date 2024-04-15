import java.time.LocalDate;

public class Abdominais extends Atividade{
    private String tipo;
    private int reps;

    

    public Abdominais(String codigo, String descricao, LocalDate data, int duracao, String tipo, int reps) {
        super(codigo, descricao, data, duracao);
        this.tipo = tipo;
        this.reps = reps;
    }
    public Abdominais(String tipo, int reps) {
        this.tipo = tipo;
        this.reps = reps;
    }
    public Abdominais(Atividade outro, String tipo, int reps) {
        super(outro);
        this.tipo = tipo;
        this.reps = reps;
    }
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getReps() {
        return reps;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }
    @Override
    public double calorias() {
        return this.getDuracao() * this.reps * 3 / 5;
        //throw new UnsupportedOperationException("Unimplemented method 'calorias'");
    }
    @Override
    public Atividade clone() {
        return new Abdominais(this.getCodigo(),this.getDescricao(),this.getData(),this.getDuracao(),this.tipo,this.reps);
        // throw new UnsupportedOperationException("Unimplemented method 'clone'");
    }

    
}
