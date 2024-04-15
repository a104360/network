import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Fitness {
    private Map<String,Utilizador> db;

    public Fitness(){
        this.db = new HashMap<>();
    }

    public Fitness(Map<String, Utilizador> db) {
        this.db = new HashMap<>(db);
    }

    public Map<String, Utilizador> getDb() {
        return new HashMap<>(db);
    }

    public void setDb(Map<String, Utilizador> db) {
        this.db = db;
    }

    public Utilizador getUtilizador(String email){
        return this.db.get(email);
    }

    public void insertUtilizador(Utilizador qjm){
        this.db.put(qjm.getEmail(),qjm);
    }

    public Utilizador removeUtilizador(Utilizador qjm){
        return this.db.remove(qjm.getEmail());
    }

    public boolean existeUtilizador(String email){
        return this.db.containsKey(email);
    }
    
    public int quantos(){
        return this.db.size();
    }

    public int quantos(String atividade,String email){
        return this.db.get(email). // Ir buscar o user
        getAtividades(). // obter as atividades
        values().stream().filter(x->x.getCodigo() == atividade). // Dos valores do Map, filtrar todos cujo codigo = codigo desejado
        mapToInt(x->1). //cada valor equivale a 1
        sum(); // Somar todos (1+1+1+...)
    }

    public void adiciona(String email,Atividade act){
        Utilizador user = this.db.get(email);
        user.addAtividade(act);
    }

    public int tempoTotalUtilizador(String email){
        return this.db.get(email).getAtividades().values().stream().mapToInt(x->x.getDuracao()).sum();
    }

    public List<Atividade> getAllAtividades(){
        List<Atividade> todas = new ArrayList<>();
        for(Utilizador user : this.db.values()){
            //todas.addAll(user.getAtividades().values());
            for(Atividade at : user.getAtividades().values()){
                todas.add(at.clone());
            }
        }

        return todas;
    }

}
