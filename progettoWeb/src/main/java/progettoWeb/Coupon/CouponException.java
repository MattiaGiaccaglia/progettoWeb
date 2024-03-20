package progettoWeb.Coupon;

public class CouponException {

    public static class CouponExceptionNotFound extends RuntimeException{
        public CouponExceptionNotFound (String message){
            super(message);
        }
    }
}
