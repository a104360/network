import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tabelas.Agencia;
import tabelas.AgentesDoPedido;
import tabelas.Cliente;
import tabelas.Despesa;
import tabelas.Funcionario;
import tabelas.Localidade;
import tabelas.Pagamento;
import tabelas.Pedido;
import tabelas.Tecnica;
import tabelas.TecnicaDoPedido;
import tabelas.Entidade;


public class ReadCSV {
    public Map<String,List<Entidade>> table;

    public ReadCSV(){
        this.table = new HashMap<>();
    }

    public ReadCSV(String[] path){
        this.table = new HashMap<>();
        this.parseAgencia(path[0]);
        this.parseAgentesDoPedido(path[1]);
        this.parseCliente(path[2]);
        this.parseDespesa(path[3]);
        this.parseFuncionario(path[4]);
        this.parseLocalidade(path[5]);
        this.parsePagamento(path[6]);
        this.parsePedido(path[7]);
        this.parseTecnica(path[8]);
        this.parseTecnicaDoPedido(path[9]);
    }

    public void parseAgencia(String path){
        Agencia b = new Agencia();
        this.table.put("Agencia",b.loadDB(path));
    }
    public void parseAgentesDoPedido(String path){
        AgentesDoPedido b = new AgentesDoPedido();
        this.table.put("AgentesDoPedido",b.loadDB(path));
    }
    public void parseCliente(String path){
        Cliente b = new Cliente();
        this.table.put("Cliente",b.loadDB(path));
    }
    public void parseDespesa(String path){
        Despesa b = new Despesa();
        this.table.put("Despesa",b.loadDB(path));
    }
    public void parseFuncionario(String path){
        Funcionario b = new Funcionario();
        this.table.put("Funcionario",b.loadDB(path));
    }
    public void parseLocalidade(String path){
        Localidade b = new Localidade();
        this.table.put("Localidade",b.loadDB(path));
    }
    public void parsePagamento(String path){
        Pagamento b = new Pagamento();
        this.table.put("Pagamento",b.loadDB(path));
    }
    public void parsePedido(String path){
        Pedido b = new Pedido();
        this.table.put("Pedido",b.loadDB(path));
    }
    public void parseTecnica(String path){
        Tecnica b = new Tecnica();
        this.table.put("Tecnica",b.loadDB(path));
    }
    public void parseTecnicaDoPedido(String path){
        TecnicaDoPedido b = new TecnicaDoPedido();
        this.table.put("TecnicaDoPedido",b.loadDB(path));
    }
}
