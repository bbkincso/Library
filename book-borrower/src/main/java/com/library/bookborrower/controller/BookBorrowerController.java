package com.library.bookborrower.controller;

import com.library.bookborrower.dto.Borrowing;
import com.library.bookborrower.feignclients.BookBorrowingFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookBorrowerController {

    @Autowired
    private BookBorrowingFeignClient bookBorrowingFeignClient;

    @RequestMapping(value = "/book-borrowing-feign/{borrowingId}", method= RequestMethod.GET)
    public Borrowing borrowBook(@PathVariable(value = "borrowingId") long borrowingId) {

        return bookBorrowingFeignClient.getBorrowingById(borrowingId);
    }
}
