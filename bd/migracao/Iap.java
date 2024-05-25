import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Iap{
    private Connection con;
    private Statement stmt;
    public static void main(String[] args){
        Iap a = new Iap();
        a.createConnection(args[0], args[1]);



        a.closeConnection();
    }
    void createConnection(String user,String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/InvestigacaoAPortuguesa", user, password);
            System.out.println("Conexao bem sucedida");
            this.stmt = this.con.createStatement();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(Iap.class.getName()).log(Level.SEVERE,null,e);
            System.err.println("Conexao falhou !A");
        } catch (SQLException s){
            Logger.getLogger(Iap.class.getName()).log(Level.SEVERE,null,s);
            System.err.println("Conexao falhou !B");
        }
    }
    void closeConnection(){
        try {
            this.con.close();
            System.out.println("Conexao encerrada com sucesso");
        } catch (SQLException e) {
            System.err.println("Desconxao falhou !C");
        } catch (NullPointerException a){
            System.err.println(a);
        }
    }
    ResultSet query(String query){
        try {
            ResultSet r = this.stmt.executeQuery(query);
            
            return r;
        } catch (SQLException e) {
            System.err.println("Query inválida!");
            return null;
        }
    }
}