public class NExistUtilizadorException extends Exception{
    public NExistUtilizadorException(){
        System.err.println("Utilizador já existe");
        printStackTrace();
    }
    public NExistUtilizadorException(String l){
        System.err.println(l);
    }
}
