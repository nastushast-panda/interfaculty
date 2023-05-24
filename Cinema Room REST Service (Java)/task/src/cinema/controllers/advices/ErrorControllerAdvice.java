package cinema.controllers.advices;

import cinema.exceptions.AlreadyPurchasedException;
import cinema.exceptions.OutOfBoundsCoordinatesException;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler({
            AlreadyPurchasedException.class,
            OutOfBoundsCoordinatesException.class,
            WrongTokenException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorDTO> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
    }
    record ErrorDTO(String error) {}

    @ExceptionHandler( {
            WrongPasswordException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<ErrorDTO> handleException(WrongPasswordException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
