public class Aluno {
    private String numero;
    public int nota;
    private String nome;
    private String curso;


    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public Aluno(String numero, int nota, String nome, String curso) {
        this.numero = numero;
        this.nota = nota;
        this.nome = nome;
        this.curso = curso;
    }
    
    public Aluno() {
        this.numero = "";
        this.nota = -1;
        this.nome = "";
        this.curso = "";
    }
    @Override
    public String toString() {
        return numero + ";" + nota + ";" + nome + ";" + curso + ";\n";
    }
    
    public boolean toEquals(Aluno a){
        return this.numero == a.numero;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + nota;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((curso == null) ? 0 : curso.hashCode());
        return result;
    }
    
    public Aluno clone(){
        return new Aluno(this.numero,this.nota,this.nome,this.curso);
    }
}
