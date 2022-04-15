package com.library.bookborrower.feignclients;

import com.library.bookborrower.dto.Borrowing;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("borrowing-service")
public interface BookBorrowingFeignClient {

    @GetMapping("borrowings")
    List<Borrowing> getAllBorrowings();

    @GetMapping("borrowings/{borrowingId}")
    Borrowing getBorrowingById(@PathVariable("borrowingId") Long borrowingId);

//    @GetMapping("borrowings/users/{userId}")
//    List<Borrowing> getBorrowingsByUser(@PathVariable("userId") Long userId);

    @GetMapping("borrowings/books/{bookId}")
    List<Borrowing> getBorrowingsByBookId(@PathVariable("bookId") Long bookId);

    @RequestMapping(value = "/borrowings", method = RequestMethod.POST)
    ResponseEntity<Borrowing> saveBorrowing(@RequestBody Borrowing book);

    @PutMapping("/borrowings/{borrowingId}")
    Borrowing updateBorrowingById(@RequestBody Borrowing borrowing, @PathVariable("borrowingId") Long borrowingId);

    @DeleteMapping("/borrowings/{borrowingId}")
    ResponseEntity<Borrowing> deleteBorrowingById(@PathVariable Long borrowingId);

}
