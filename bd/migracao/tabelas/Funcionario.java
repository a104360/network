package tabelas;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Funcionario extends Entidade{
    private String nome;
    private double salario;
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


    public Funcionario(){
        this.nome = null;
        this.salario = 0;
        this.inicioContrato = null;
        this.fimContrato = null;
        this.departamento = null;
        this.posicao = null;
        this.dataNascimento = null;
        this.porta = 0;
        this.localidade = 0;
        this.codigoPostal = 0;
        this.numero = 0;
        this.email = null;
        this.agencia = 0;
    }

    public Funcionario(String nome, double salario, Date inicioContrato, Date fimContrato, String departamento,
            String posicao, Date dataNascimento, int porta, int localidade, int codigoPostal, int numero, String email,
            int agencia) {
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
        DecimalFormat df = new DecimalFormat("0.00");
        String answer = "('" + nome + "'," + df.format(this.salario) + ",'"
        + inicioContrato + "',";
        
        if(this.fimContrato == null){
            answer = answer + "NULL,";
        } else {
            answer = answer + "'" + this.fimContrato + "',";
        }
    
        answer += "'" + this.departamento + "','" + posicao + "','" + dataNascimento + "'," + porta + "," + localidade
        + "," + codigoPostal + "," + numero + ",'" + email + "'," + agencia
        + ")";
        return answer;
    }
    
    public void load(String line){
        String[] values = line.split(",");
        this.nome = values[0];
        this.salario = Double.parseDouble(values[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.inicioContrato = Date.valueOf(LocalDate.parse(values[2],formatter));
        if(values[3] != "")this.fimContrato = Date.valueOf(LocalDate.parse(values[3],formatter));
        this.departamento = values[4];
        this.posicao = values[5];
        this.dataNascimento = Date.valueOf(LocalDate.parse(values[6],formatter));
        this.porta = Integer.parseInt(values[7]);
        this.localidade = Integer.parseInt(values[8]);
        this.codigoPostal = Integer.parseInt(values[9]);
        this.numero = Integer.parseInt(values[10]);
        this.email = values[11];
        this.agencia = Integer.parseInt(values[12]);
    }

    public Entidade createInstance(){
        return new Funcionario();
    }
    
}