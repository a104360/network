package tabelas;

public class Cliente extends Entidade{
    private String nome;
    private String registoCriminal;
    private int nif;
    private int porta;
    private int localidade;
    private int codigoPostal;
    private int numero;
    private String email;
    
    
    public Cliente() {
        this.nome = null;
        this.registoCriminal = null;
        this.nif = 0;
        this.porta = 0;
        this.localidade = 0;
        this.codigoPostal = 0;
        this.numero = 0;
        this.email = null;
    }


    public Cliente(String nome, String registoCriminal, int nif, int porta, int localidade, int codigoPostal,
            int numero, String email) {
        this.nome = nome;
        this.registoCriminal = registoCriminal;
        this.nif = nif;
        this.porta = porta;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
        this.numero = numero;
        this.email = email;
    }
    
    
    @Override
    public String toString() {
        String answer = "('" + nome + "',";
        if(this.registoCriminal == null){
            answer += "NULL,";
        } else {
            answer += "'" + this.registoCriminal + "',";
        }
         answer += nif + "," + porta + "," + localidade + "," + codigoPostal + ","
        + numero + ",'" + email + "')";
        return answer;
    }
    
    public void load(String line){
        String[] values = line.split(",");
        this.nome = values[0];
        this.registoCriminal = values[1];
        this.nif = Integer.parseInt(values[2]);
        this.porta = Integer.parseInt(values[3]);
        this.localidade = Integer.parseInt(values[4]);
        this.codigoPostal = Integer.parseInt(values[5]);
        this.numero = Integer.parseInt(values[6]);
        this.email = values[7];
    }

    public Entidade createInstance(){
        return new Cliente();
    }

}