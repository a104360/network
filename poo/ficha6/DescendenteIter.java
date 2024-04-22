import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DescendenteIter implements Iterator<Utilizador> {
    private List<Utilizador> utilizadores;
    private int next = 0;

    public DescendenteIter(List<Utilizador> u){
        this.utilizadores = u;
    }    

    @Override
    public boolean hasNext(){
        return next < this.utilizadores.size();
    }
    @Override
    public Utilizador next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        return this.utilizadores.get(next);
    }
}
