package tabelas;

public class Cliente{
    private int id;
    private String nome;
    private String registoCriminal;
    private int nif;
    private int porta;
    private int localidade;
    private int codigoPostal;
    private int numero;
    private String email;

    
    public Cliente(int id, String nome, String registoCriminal, int nif, int porta, int localidade, int codigoPostal,
            int numero, String email) {
        this.id = id;
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
        return "Cliente [id=" + id + ", nome=" + nome + ", registoCriminal=" + registoCriminal + ", nif=" + nif
                + ", porta=" + porta + ", localidade=" + localidade + ", codigoPostal=" + codigoPostal + ", numero="
                + numero + ", email=" + email + "]";
    }

    

}