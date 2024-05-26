import org.junit.jupiter.api.Test;

public class ReadCSVTest {
    @Test
    void testParseAgencia() {
        ReadCSV r = new ReadCSV();
        r.parseAgencia("/home/retr0/Downloads/PovoamentoCsv/Agencia.csv");
        System.out.println(r.table);
    }
    
    @Test
    void testParseAgenteDoPedido() {
        ReadCSV r = new ReadCSV();
        r.parsePedido("/home/retr0/Downloads/PovoamentoCsv/Pedido.csv");
        System.out.println(r.table);
    }

    @Test
    void testParseCliente() {
        ReadCSV r = new ReadCSV();
        r.parseCliente("/home/retr0/Downloads/PovoamentoCsv/Cliente.csv");
        System.out.println(r.table);    
    }

    @Test
    void testParseDespesa() {
        ReadCSV r = new ReadCSV();
        r.parseDespesa("/home/retr0/Downloads/PovoamentoCsv/Despesa.csv");
        System.out.println(r.table);
    }

    @Test
    void testParseFuncionario() {
        ReadCSV r = new ReadCSV();
        r.parseFuncionario("/home/retr0/Downloads/PovoamentoCsv/Funcionario.csv");
        System.out.println(r.table);
    }

    @Test
    void testParseLocalidade() {
        ReadCSV r = new ReadCSV();
        r.parseLocalidade("/home/retr0/Downloads/PovoamentoCsv/Localidade.csv");
        System.out.println(r.table);
    }

    @Test
    void testParsePagamento() {
        ReadCSV r = new ReadCSV();
        r.parsePagamento("/home/retr0/Downloads/PovoamentoCsv/Pagamento.csv");
        System.out.println(r.table);
    }

    @Test
    void testParsePedido() {
        ReadCSV r = new ReadCSV();
        r.parsePedido("/home/retr0/Downloads/PovoamentoCsv/Pedido.csv");
        System.out.println(r.table);
    }

    @Test
    void testParseTecnica() {
        ReadCSV r = new ReadCSV();
        r.parseTecnica("/home/retr0/Downloads/PovoamentoCsv/Tecnica.csv");
        System.out.println(r.table);
    }

    @Test
    void testParseTecnicaDoPedido() {
        ReadCSV r = new ReadCSV();
        r.parseTecnicaDoPedido("/home/retr0/Downloads/PovoamentoCsv/TecnicaDoPedido.csv");
        System.out.println(r.table);
    }

    @Test
    void testReadCSV(){
        String[] paths = {
            "/home/retr0/Downloads/PovoamentoCsv/Agencia.csv",
            "/home/retr0/Downloads/PovoamentoCsv/AgentesDoPedido.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Cliente.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Despesa.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Funcionario.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Localidade.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Pagamento.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Pedido.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Tecnica.csv",
            "/home/retr0/Downloads/PovoamentoCsv/TecnicaDoPedido.csv"
        };
        ReadCSV r = new ReadCSV(paths);
        System.out.println(r.table.get("AgenteDoPedido").toString());
    }
    
    @Test 
    void testGetQueryAgencia(){
        String[] paths = {
            "/home/retr0/Downloads/PovoamentoCsv/Agencia.csv",
            "/home/retr0/Downloads/PovoamentoCsv/AgentesDoPedido.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Cliente.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Despesa.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Funcionario.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Localidade.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Pagamento.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Pedido.csv",
            "/home/retr0/Downloads/PovoamentoCsv/Tecnica.csv",
            "/home/retr0/Downloads/PovoamentoCsv/TecnicaDoPedido.csv"
        };
        ReadCSV r = new ReadCSV(paths);
        //System.out.println(r.getQueryAgencia());
        //System.out.println(r.getQueryAgentesDoPedido());
        //System.out.println(r.getQueryCliente());
        //System.out.println(r.getQueryFuncionario());
        //System.out.println(r.getQueryLocalidade());
        //System.out.println(r.getQueryPagamento());
        //System.out.println(r.getQueryPedido());
        System.out.println(r.getQueryTecnica());
        System.out.println(r.getQueryTecnicaDoPedido());
        System.out.println(r.getQueryDespesa());
    }
}
