package tabelas;

public class Despesa extends Entidade{
    private int bonus;
    private int alimentacao;
    private int alojamento;
    private int equipamento;
    private int outros;

    public Despesa(int bonus, int alimentacao, int alojamento, int equipamento, int outros) {
        this.bonus = bonus;
        this.alimentacao = alimentacao;
        this.alojamento = alojamento;
        this.equipamento = equipamento;
        this.outros = outros;
    }
    
    public Despesa(){
        this.bonus = 0;
        this.alimentacao = 0;
        this.alojamento = 0;
        this.equipamento = 0;
        this.outros = 0;
    }
    
    @Override
    public String toString() {
        return "Despesa [bonus=" + bonus + ", alimentacao=" + alimentacao + ", alojamento=" + alojamento
        + ", equipamento=" + equipamento + ", outros=" + outros + "]";
    }
    
    public void load(String line){
        String[] values = line.split(",");
        this.bonus = Integer.parseInt(values[0]);
        this.alimentacao = Integer.parseInt(values[1]);
        this.alojamento = Integer.parseInt(values[2]);
        this.equipamento = Integer.parseInt(values[3]);
        this.outros = Integer.parseInt(values[4]);
    } 

    public Entidade createInstance(){
        return new Despesa();
    }

}
