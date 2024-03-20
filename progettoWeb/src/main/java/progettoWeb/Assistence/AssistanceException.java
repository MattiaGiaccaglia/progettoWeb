package progettoWeb.Assistence;

public class AssistanceException {

    public static class AssistanceExceptionNotFound extends RuntimeException{
        public AssistanceExceptionNotFound (String message){
            super(message);
        }
    }
}
