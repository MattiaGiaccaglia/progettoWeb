package progettoWeb.FidelityCard;

public class FidelityCardException {

    public static class FidelityCardExceptionNotFound extends RuntimeException{
        public FidelityCardExceptionNotFound (String message){
            super(message);
        }
    }
}
