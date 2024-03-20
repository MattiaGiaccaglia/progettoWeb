package progettoWeb.Review;

public class ReviewException {

    public static class ReviewExceptionNotFound extends RuntimeException{
        public ReviewExceptionNotFound (String message){
            super(message);
        }
    }
}
