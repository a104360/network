import java.util.Comparator;

public class OrdenarPorNome implements Comparator<Utilizador>{
        public int compare(Utilizador a, Utilizador b){
            return a.getNome().compareToIgnoreCase(b.getNome());
        }
}
