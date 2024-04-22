import java.util.Comparator;


public class OrdenarPorEmail implements Comparator<Utilizador>{
    public int compare(Utilizador a,Utilizador b){
        return a.getEmail().compareToIgnoreCase(b.getEmail());
    }
}