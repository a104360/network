package tabelas;

import java.sql.Date;

public class Funcionario{
    private int id;
    private String nome;
    private int salario;
    private Date inicioContrato;
    private Date fimContrato;
    private String departamento;
    private String posicao;
    private Date dataNascimento;
    private int porta;
    private int localidade;
    private int codigoPostal;
    private int numero;
    private String email;
    private int agencia;

    
    public Funcionario(int id, String nome, int salario, Date inicioContrato, Date fimContrato, String departamento,
            String posicao, Date dataNascimento, int porta, int localidade, int codigoPostal, int numero, String email,
            int agencia) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.inicioContrato = inicioContrato;
        this.fimContrato = fimContrato;
        this.departamento = departamento;
        this.posicao = posicao;
        this.dataNascimento = dataNascimento;
        this.porta = porta;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
        this.numero = numero;
        this.email = email;
        this.agencia = agencia;
    }


    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", nome=" + nome + ", salario=" + salario + ", inicioContrato="
                + inicioContrato + ", fimContrato=" + fimContrato + ", departamento=" + departamento + ", posicao="
                + posicao + ", dataNascimento=" + dataNascimento + ", porta=" + porta + ", localidade=" + localidade
                + ", codigoPostal=" + codigoPostal + ", numero=" + numero + ", email=" + email + ", agencia=" + agencia
                + "]";
    }

    
}