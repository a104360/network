import java.util.Comparator;

public class OrdenarPorDedicacao implements Comparator<Utilizador>{
    public int compare(Utilizador a, Utilizador b){
        double hardA = a.getAtividades().values().stream().mapToDouble(x->x.calorias()).max().getAsDouble();
        double hardB = b.getAtividades().values().stream().mapToDouble(x->x.calorias()).max().getAsDouble();
        if(hardA == hardB) return 0;
        if(hardA < hardB) return -1;
        return 1;
    }
}
