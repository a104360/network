package tabelas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Entidade {

    public abstract Entidade createInstance();
    
    @Override
    public abstract String toString();
    
    public abstract void load(String file);
    
    public List<Entidade> loadDB(String path) {
        List<Entidade> list = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                Entidade n = createInstance();
                n.load(line);
                list.add(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
