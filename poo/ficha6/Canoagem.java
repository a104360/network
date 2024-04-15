import java.time.LocalDate;

public class Canoagem extends Atividade{
    private String barco;
    private int speedVento;
    private int direcaoVento; // Radianos
    private int distanciaPercorrida;
    private int laps;

    public Canoagem(){
        super();
        this.barco = "K2";
        this.speedVento = 0;
        this.direcaoVento = 0;
        this.distanciaPercorrida = 0;
        this.laps = 0;
    }

    public Canoagem(String codigo, String descricao, LocalDate data, int duracao, String barco, int speedVento,
            int direcaoVento, int distanciaPercorrida, int laps) {
        super(codigo, descricao, data, duracao);
        this.barco = barco;
        this.speedVento = speedVento;
        this.direcaoVento = direcaoVento;
        this.distanciaPercorrida = distanciaPercorrida;
        this.laps = laps;
    }

    public Canoagem(String barco, int speedVento, int direcaoVento, int distanciaPercorrida, int laps) {
        this.barco = barco;
        this.speedVento = speedVento;
        this.direcaoVento = direcaoVento;
        this.distanciaPercorrida = distanciaPercorrida;
        this.laps = laps;
    }

    public Canoagem(Atividade outro, String barco, int speedVento, int direcaoVento, int distanciaPercorrida,
            int laps) {
        super(outro);
        this.barco = barco;
        this.speedVento = speedVento;
        this.direcaoVento = direcaoVento;
        this.distanciaPercorrida = distanciaPercorrida;
        this.laps = laps;
    }

    public String getBarco() {
        return barco;
    }

    public void setBarco(String barco) {
        this.barco = barco;
    }

    public int getSpeedVento() {
        return speedVento;
    }

    public void setSpeedVento(int speedVento) {
        this.speedVento = speedVento;
    }

    public int getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(int direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(int distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    @Override
    public double calorias() {
        return this.distanciaPercorrida * (this.speedVento) * this.getDuracao() * this.getUser().getIdade() / 4 ;
        //throw new UnsupportedOperationException("Unimplemented method 'calorias'");
    }

    @Override
    public Atividade clone() {
        return new Canoagem(this.getCodigo(), this.getDescricao(), this.getData(), this.getDuracao(), this.barco, this.speedVento,
        this.direcaoVento, this.distanciaPercorrida, this.laps);
        //throw new UnsupportedOperationException("Unimplemented method 'clone'");
        
    }
    
}
