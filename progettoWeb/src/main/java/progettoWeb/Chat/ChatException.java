package progettoWeb.Chat;

public class ChatException {
    public static class ChatExceptionNotFound extends RuntimeException{
        public ChatExceptionNotFound (String message){
            super(message);
        }
    }
}
