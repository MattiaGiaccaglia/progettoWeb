package progettoWeb.User;

public class UserException {

    public static class UserExceptionNotFound extends RuntimeException{
        public UserExceptionNotFound (String message){
            super(message);
        }
    }
}
