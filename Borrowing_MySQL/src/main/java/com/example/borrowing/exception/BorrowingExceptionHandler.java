package com.example.borrowing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class BorrowingExceptionHandler {

    @ExceptionHandler(value = {BorrowingNotFoundException.class})
    public ResponseEntity<Object> handleBorrowingNotFoundException(BorrowingNotFoundException e){
        BorrowingException borrowingException = new BorrowingException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(borrowingException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object>customValidationErrorHandling(MethodArgumentNotValidException e) {
        BorrowingException borrowingException = new BorrowingException(
                "Validation Error " + e.getBindingResult().getFieldError().getDefaultMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(borrowingException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object>handleGlobalException(Exception e, WebRequest request) {
        BorrowingException borrowingException = new BorrowingException(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(borrowingException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
