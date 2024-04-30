
public class Turma {
    private String designacao;
    private Aluno[] alunos;
    private int capacidade;
    private int ocupacao;
    private final int capacidadeInicial = 20;


    
    

    public String getDesignacao() {
        return designacao;
    }
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public int getOcupacao() {
        return ocupacao;
    }
    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    public Aluno[] getAlunos(){
        Aluno[] alunosClonados = new Aluno[this.ocupacao];

        for (int i = 0; i < this.ocupacao; i++) {
            alunosClonados[i] = this.alunos[i].clone();   
        }

        return alunosClonados;
    }

    public void insereAluno(Aluno a){
        this.alunos[this.ocupacao] = new Aluno(a.getNumero(),a.getNota(),a.getNome(),a.getCurso());
        this.ocupacao++;
    }
    public void insereAluno(String numero,int nota, String nome, String curso){
        this.alunos[this.ocupacao] = new Aluno(numero,nota,nome,curso);
        this.ocupacao++;
    }

    public void printAlunos(){
        for (int i = 0; i < this.ocupacao; i++) {
            System.out.print(this.alunos[i]);
        }
    }

    public Turma(String designacao, int capacidade, int ocupacao, Aluno[] alunos) {
        this.designacao = designacao;
        this.capacidade = capacidade;
        this.ocupacao = alunos.length;
        this.alunos = new Aluno[alunos.length];
        for (int i = 0; i < alunos.length; i++) {
            this.alunos[i] = alunos[i].clone();
        }
    }
    public Turma() {
        this.designacao = "";
        this.capacidade = 0;
        this.ocupacao = 0;
    }
    
    @Override
    public String toString() {
        return "Turma [designacao=" + designacao + ", capacidade=" + capacidade + ", ocupacao=" + ocupacao
                + ", capacidadeInicial=" + capacidadeInicial + "]";
    }
    
    
}
