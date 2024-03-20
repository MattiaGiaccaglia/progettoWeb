package progettoWeb.Store;

public class StoreException extends RuntimeException{
    public static class StoreExceptionNotFound extends RuntimeException{
        public StoreExceptionNotFound (String message){
            super(message);
        }
    }
}
