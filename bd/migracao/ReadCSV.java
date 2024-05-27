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


    public String getQueryAgencia(){
        List<Entidade> list = this.table.get("Agencia");
        String answer = "INSERT INTO Agencia (email,numero,porta,localidade,codigoPostal) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Agencia a = (Agencia) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryAgentesDoPedido(){
        List<Entidade> list = this.table.get("AgentesDoPedido");
        String answer = "INSERT INTO AgentesDoPedido (idPedido,idAgente) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            AgentesDoPedido a = (AgentesDoPedido) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryCliente(){
        List<Entidade> list = this.table.get("Cliente");
        String answer = "INSERT INTO Cliente (nome, registoCriminal, NIF, porta, localidade, codigoPostal, numero, email) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Cliente a = (Cliente) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryFuncionario(){
        List<Entidade> list = this.table.get("Funcionario");
        String answer = "INSERT INTO Funcionario (nome, salario, inicioContrato, fimContrato, departamento, posicao, dataNascimento, porta, localidade, codigoPostal, numero, email, agencia) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Funcionario a = (Funcionario) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryLocalidade(){
        List<Entidade> list = this.table.get("Localidade");
        String answer = "INSERT INTO Localidade (nome) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Localidade a = (Localidade) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryPagamento(){
        List<Entidade> list = this.table.get("Pagamento");
        String answer = "INSERT INTO Pagamento (estado, valor, metodo, data) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Pagamento a = (Pagamento) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryPedido(){
        List<Entidade> list = this.table.get("Pedido");
        String answer = "INSERT INTO Pedido (nivel, tipo, relatorio, status, inicio, fim, idPagamento, idCliente, idAgencia, idDespesa) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Pedido a = (Pedido) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryTecnica(){
        List<Entidade> list = this.table.get("Tecnica");
        String answer = "INSERT INTO Tecnica (nome) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Tecnica a = (Tecnica) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryTecnicaDoPedido(){
        List<Entidade> list = this.table.get("TecnicaDoPedido");
        String answer = "INSERT INTO TecnicaDoPedido (pedido,tecnica) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            TecnicaDoPedido a = (TecnicaDoPedido) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
    public String getQueryDespesa(){
        List<Entidade> list = this.table.get("Despesa");
        String answer = "INSERT INTO Despesa (bonus, alimentacao, alojamento, equipamento, outros) VALUES ";
        int size = list.size();
        for(Entidade e : list){
            Despesa a = (Despesa) e;
            answer = answer + a.toString();
            if(size-1 != 0) answer = answer + ",";
            size--;
        }
        answer = answer + ";";
        return answer;
    }
    
}
