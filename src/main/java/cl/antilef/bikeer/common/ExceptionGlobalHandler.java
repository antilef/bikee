package cl.antilef.bikeer.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice()
public class ExceptionGlobalHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialHandler(Exception exception, WebRequest request) {
        return new ResponseEntity<>(

                new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false)
                ),

                HttpStatus.UNAUTHORIZED
        );
    }
}
