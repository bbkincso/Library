package com.example.demo.controller;

import com.example.demo.dto.Borrowing;
import com.example.demo.feignclients.BookBorrowingFeignClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookBorrowerController {

    Logger log = LoggerFactory.getLogger(BookBorrowerController.class);

    @Autowired
    private BookBorrowingFeignClients bookBorrowingFeignClient;

    @RequestMapping(value = "/borrowing-client/{borrowingId}", method= RequestMethod.GET)
    public Borrowing borrowBook(@PathVariable(value = "borrowingId") Long borrowingId) {
        log.info("borrowBook called");
        return bookBorrowingFeignClient.getBorrowingById(borrowingId);
    }

    @RequestMapping("/borrowing-client")
    List<Borrowing> sortAllBorrowings() {
        log.info("sortAllBorrowings called");
        return bookBorrowingFeignClient.getAllBorrowings();
    }

    //get borrowings by books
    @RequestMapping("/borrowing-client/books/{bookId}")
    List<Borrowing>findBorrowingsByBookId(@PathVariable("bookId") Long bookId) {
        return bookBorrowingFeignClient.getBorrowingsByBookId(bookId);
    }

    // create borrowing
    @PostMapping("/borrowing-client")
    ResponseEntity<Borrowing> createBorrowing(@RequestBody Borrowing borrowing) {
        return bookBorrowingFeignClient.saveBorrowing(borrowing);
    }

    // update borrowing
    @PutMapping("borrowing-client/{borrowingId}")
    Borrowing updateBorrowing(@RequestBody Borrowing borrowing, @PathVariable("borrowingId") Long borrowingId)
    {
        return bookBorrowingFeignClient.updateBorrowingById(borrowing, borrowingId);
    }

    // delete borrowing
    @DeleteMapping("/borrowing-client/{borrowingId}")
    ResponseEntity<Borrowing> deleteBorrowingById(@PathVariable("borrowingId") Long borrowingId) {
        return bookBorrowingFeignClient.deleteBorrowingById(borrowingId);
    }
}