package br.com.fiap.money_flow_api.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {
    
    // Um record é uma forma compacta e imutável de declarar uma classe que serve apenas para armazenar dados.
    record ValidationError(String field, String message) {
        // construtor adicional para o record que aceita um objeto do tipo FieldError
        public ValidationError(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidationError> handle(MethodArgumentNotValidException e) {
        return e.getFieldErrors()
                    .stream()
                    .map(ValidationError::new)
                    .toList();
    }
}
