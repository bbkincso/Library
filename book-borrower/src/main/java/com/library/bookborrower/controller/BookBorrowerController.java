package com.library.bookborrower.controller;

import com.library.bookborrower.dto.Borrowing;
import com.library.bookborrower.feignclients.BookBorrowingFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class BookBorrowerController {

    @Autowired
    private BookBorrowingFeignClient bookBorrowingFeignClient;

    @RequestMapping(value = "/borrowing-client/{borrowingId}", method= RequestMethod.GET)
    public Borrowing borrowBook(@PathVariable(value = "borrowingId") Long borrowingId) {
        return bookBorrowingFeignClient.getBorrowingById(borrowingId);
    }

    @RequestMapping("/borrowing-client")
    List<Borrowing> sortAllBorrowings() {
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
