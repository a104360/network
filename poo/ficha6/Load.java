import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class Load {
    private File saveFile;

    public Load(){
        this.saveFile = new File("users.csv");
    }

    public Load(String s){
        this.saveFile = new File(s);
    }

    public Load(File f){
        String l = f.getAbsolutePath();
        this.saveFile = new File(l);
    }

    public File getSaveFile() {
        return saveFile;
    }

    public void setSaveFile(File saveFile) {
        this.saveFile = new File(saveFile.getAbsolutePath());
    }
    

    public void saveAtividadesUtilizador(Utilizador u) throws IOException{
        if(this.saveFile.exists() == false){
            this.saveFile.createNewFile();
        }
        FileWriter fw = new FileWriter(this.saveFile);
        for(Atividade a : u.getAtividades().values()){
            fw.write(a.toString());
        }
        fw.close();
    }

    public void readAtividadesUtilizador(Utilizador u) throws IOException{
        if(this.saveFile.canRead()){
            Set<Atividade> at = new TreeSet<>();
            Scanner scanner = new Scanner(this.saveFile);
            while(scanner.hasNextLine()){
                // Parse de uma atividade
            }
        }
    }

}
