public class ExistUtilizardorException extends Exception{
    public ExistUtilizardorException(){
        System.err.println("Tal utilizador já existe");
    }
    public ExistUtilizardorException(String l) {
        System.err.println(l);
    }
}
