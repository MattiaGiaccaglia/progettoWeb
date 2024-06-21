package progettoWeb.Messages;

public class MessageException {

    public static class MessageExceptionNotFound extends RuntimeException{
        public MessageExceptionNotFound (String message){
            super(message);
        }
    }
}
