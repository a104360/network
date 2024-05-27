import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;

import tabelas.Entidade;


public class Iap{
    private Connection con;
    private Statement stmt;
    private static final String[] paths = {
        "PovoamentoCsv/Agencia.csv",
        "PovoamentoCsv/AgentesDoPedido.csv",
        "PovoamentoCsv/Cliente.csv",
        "PovoamentoCsv/Despesa.csv",
        "PovoamentoCsv/Funcionario.csv",
        "PovoamentoCsv/Localidade.csv",
        "PovoamentoCsv/Pagamento.csv",
        "PovoamentoCsv/Pedido.csv",
        "PovoamentoCsv/Tecnica.csv",
        "PovoamentoCsv/TecnicaDoPedido.csv"
    };
    public static void main(String[] args){
        Iap a = new Iap();
        a.createConnection(args[0], args[1]);

        ReadCSV databases = new ReadCSV(Iap.paths);
        a.query(databases.getQueryPagamento());
        a.query(databases.getQueryDespesa());
        a.query(databases.getQueryLocalidade());
        a.query(databases.getQueryAgencia());
        a.query(databases.getQueryFuncionario());
        a.query(databases.getQueryCliente());
        a.query(databases.getQueryPedido());
        a.query(databases.getQueryAgentesDoPedido());
        a.query(databases.getQueryTecnica());
        a.query(databases.getQueryTecnicaDoPedido());

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

    void query(String query){
        try {
            System.out.println(this.stmt.executeUpdate(query));
        } catch (SQLException e) {
            System.err.println("Query inválida!");
            System.err.println(e);
        }
    }
}