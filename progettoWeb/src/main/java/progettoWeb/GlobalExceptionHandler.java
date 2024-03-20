package progettoWeb;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import progettoWeb.Assistence.AssistanceException;
import progettoWeb.Chat.ChatException;
import progettoWeb.Coupon.CouponException;
import progettoWeb.FidelityCard.FidelityCardException;
import progettoWeb.Review.ReviewException;
import progettoWeb.Store.StoreException;
import progettoWeb.User.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Exception ID non intero
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException e) {
        return new ResponseEntity<>("Il valore inserito per l'ID non è valido. Assicurati di inserire un intero.", HttpStatus.BAD_REQUEST);
    }

    //Exception Coupon ID non esiste
    @ExceptionHandler(CouponException.CouponExceptionNotFound.class)
    public ResponseEntity<Object> handleCouponNotFoundException(CouponException.CouponExceptionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception User ID non esiste
    @ExceptionHandler(UserException.UserExceptionNotFound.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserException.UserExceptionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception Store ID non esiste
    @ExceptionHandler(StoreException.StoreExceptionNotFound.class)
    public ResponseEntity<Object> handleStoreNotFoundException(StoreException.StoreExceptionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception Assistance ID non esiste
    @ExceptionHandler(AssistanceException.AssistanceExceptionNotFound.class)
    public ResponseEntity<Object> handleAssistanceNotFoundException(AssistanceException.AssistanceExceptionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception Fidelity Card ID non esiste
    @ExceptionHandler(FidelityCardException.FidelityCardExceptionNotFound.class)
    public ResponseEntity<Object> handleFidelityCardNotFoundException(FidelityCardException.FidelityCardExceptionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception Chat ID non esiste
    @ExceptionHandler(ChatException.ChatExceptionNotFound.class)
    public ResponseEntity<Object> handleChatIdNotFoundException(ChatException.ChatExceptionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Exception Review ID non esiste
    @ExceptionHandler(ReviewException.ReviewExceptionNotFound.class)
    public ResponseEntity<Object> handleReviewIdNotFoundException(ReviewException.ReviewExceptionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Gestione delle eccezioni di violazione del vincolo d'integrità dei dati
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<>("Errore di violazione di vincolo di integrità dei dati: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Gestione delle eccezioni per errori di sintassi nel JSON della richiesta
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Errore di sintassi nel JSON della richiesta.", HttpStatus.BAD_REQUEST);
    }

    //Gestione delle eccezioni di NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<>("Si è verificato un errore: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}