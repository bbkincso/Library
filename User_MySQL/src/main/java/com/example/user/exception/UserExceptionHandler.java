package com.example.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        UserException userException = new UserException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(userException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object>customValidationErrorHandling(MethodArgumentNotValidException e) {
        UserException userException = new UserException(
                "Validation Error " + e.getBindingResult().getFieldError().getDefaultMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(userException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object>handleGlobalException(Exception e, WebRequest request) {
        UserException userException = new UserException(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(userException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
