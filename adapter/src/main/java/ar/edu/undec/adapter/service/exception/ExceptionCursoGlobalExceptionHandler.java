package ar.edu.undec.adapter.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;


@ControllerAdvice
public class ExceptionCursoGlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidLevelException(HttpMessageNotReadableException ex, WebRequest request) {
        String message = "El nivel ingresado no es válido. Los valores aceptados son: INICIAL, MEDIO, AVANZADO.";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
