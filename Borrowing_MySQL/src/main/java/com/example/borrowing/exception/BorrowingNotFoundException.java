package com.example.borrowing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BorrowingNotFoundException extends RuntimeException{

    public BorrowingNotFoundException(String message){
        super(message);
    }

    public BorrowingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
