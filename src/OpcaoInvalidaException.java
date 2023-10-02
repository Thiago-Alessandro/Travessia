public class OpcaoInvalidaException extends Exception{

    public OpcaoInvalidaException(int num){
        super("\nA opção \"" + num + "\" é inválida!");
    }

}
