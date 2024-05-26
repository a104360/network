package tabelas;

import java.util.ArrayList;
import java.util.List;

public class Agencia extends Entidade{
    private String email;
    private int numero;
    private int porta;
    private int localidade;
    private int codigoPostal;


    public Agencia( String email, int numero, int porta, int localidade, int codigoPostal) {
        this.email = email;
        this.numero = numero;
        this.porta = porta;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
    }
    public Agencia() {
        this.email = null;
        this.numero = 0;
        this.porta = 0;
        this.localidade = 0;
        this.codigoPostal = 0;
    }
    
    
    @Override
    public String toString() {
        return "Agencia [email=" + email + ", numero=" + numero + ", porta=" + porta + ", localidade="
        + localidade + ", codigoPostal=" + codigoPostal + "]";
    }
    
    public void load(String line){
        String[] values = line.split(",");
        this.email = values[0];
        this.numero = Integer.parseInt(values[1]);
        this.porta = Integer.parseInt(values[2]);
        this.localidade = Integer.parseInt(values[3]);
        this.codigoPostal = Integer.parseInt(values[4]);
    }
    
    public Entidade createInstance(){
        return new Agencia();
    }
}