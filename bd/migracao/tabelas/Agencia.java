package tabelas;

public class Agencia{
    private int id;
    private String email;
    private int numero;
    private int porta;
    private int localidade;
    private int codigoPostal;


    public Agencia(int id, String email, int numero, int porta, int localidade, int codigoPostal) {
        this.id = id;
        this.email = email;
        this.numero = numero;
        this.porta = porta;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
    }


    @Override
    public String toString() {
        return "Agencia [id=" + id + ", email=" + email + ", numero=" + numero + ", porta=" + porta + ", localidade="
                + localidade + ", codigoPostal=" + codigoPostal + "]";
    }

}