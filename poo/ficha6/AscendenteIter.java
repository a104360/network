import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AscendenteIter implements Iterator<Utilizador> {
    private List<Utilizador> utilizadores;
    private int next = 0;

    public AscendenteIter(List<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }



    @Override
    public boolean hasNext() {
        return next < utilizadores.size();
    }

    @Override
    public Utilizador next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        return utilizadores.get(next);
    }
    
}
