import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;



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

    public void adiciona(String email,Set<Atividade> activs){
        Utilizador u = this.db.get(email);
        for(Atividade ac : activs){
            u.addAtividade(ac);
        }
    }

    public Atividade atividadeMaisExigente(){
        Atividade a = null;
        double max = 0;
        for(Utilizador user : this.db.values()){
            for(Atividade aCiclo : user.getAtividades().values()){
                double calAtividade = a.calorias();
                if(max < calAtividade){
                    max = calAtividade;
                    a = aCiclo;
                }
            }
        }
        return a;
    }

    public Utilizador utilizadorMaisAtivo(){
        Utilizador u = null;
        double max = 0;
        for(Utilizador user : this.db.values()){
            double consumidas = user.getAtividades().values().stream().mapToDouble(x->x.calorias()).sum();
            if(consumidas > max) {
                max = consumidas;
                u = user.clone();
            }
        }
        return u;
    }

    public void actualizaDesportoFav(){
        for(Utilizador u : this.db.values()){

            Map<Atividade,Integer> atividadeMap = new TreeMap<>();
            for(Atividade a : u.getAtividades().values()){
                atividadeMap.put(a.clone(),atividadeMap.getOrDefault(a.getCodigo(),0) + 1);                
            }

            int maxCount = 0;
            Atividade favoriteSport = null;
            for (Map.Entry<Atividade, Integer> entry : atividadeMap.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    favoriteSport = entry.getKey();
                }
            }
            
            u.setDesporto_favorito(favoriteSport.getCodigo());
        }
    }

    public Utilizador utilizadorComMaisRegistos(){
        Utilizador rei = null;
        int registosMax = 0;

        for(Utilizador u : this.db.values()){

            int n = u.getAtividades().values().stream().mapToInt(x->1).sum();
            if(n > registosMax){
                registosMax = n;
                rei = u.clone();
            }

        }
        return rei;
    }
    
    public Set<Utilizador> ordenarUtilizadoresSet(){
        Set<Utilizador> sortedUsers = new TreeSet<>(Comparator.comparing(Utilizador::caloriasUtilizador).thenComparing(Utilizador::getNome));
        sortedUsers.addAll(this.db.values());
        return sortedUsers;
    }

    public List<Utilizador> ordenarUtilizadoresList(){
        List<Utilizador> sortedUsers = new ArrayList<>(this.db.values());
        sortedUsers.sort(Comparator.comparing(Utilizador::caloriasUtilizador).thenComparing(Utilizador::getNome));
        return sortedUsers;
    }

    public List<Utilizador> ordenarUtilizadorList(Comparator<Utilizador> c){
        List<Utilizador> sortedUsers = new ArrayList<>(this.db.values());
        sortedUsers.sort(c);
        return sortedUsers;
    }

    public Iterator<Utilizador> ordenarUtilizador(String criterio) throws NoSuchMethodException{
        if(criterio.compareToIgnoreCase("ascendente") == 0) return new AscendenteIter(new ArrayList<>(this.db.values()));
        if(criterio.compareToIgnoreCase("descendente") == 0) return new DescendenteIter(new ArrayList<>(this.db.values()));
        throw new NoSuchMethodException();
    }

    /*public Map<String, List<Utilizador>> podiumPorActiv(){
        Map<String,List<Utilizador>> podium = new HashMap<>();
        // Corrida 
        
        // Canoagem 
        // Abdominais
    }*/
    




}
