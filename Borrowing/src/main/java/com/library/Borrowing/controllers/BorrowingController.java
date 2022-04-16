package com.library.Borrowing.controllers;

import com.library.Borrowing.dao.BorrowingRepository;
import com.library.Borrowing.dto.Book;
import com.library.Borrowing.dto.Borrowing;
import com.library.Borrowing.exceptions.BorrowingNotFoundException;
import com.library.Borrowing.feignclients.BookFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Service
public class BorrowingController {

    Logger log = LoggerFactory.getLogger(BorrowingController.class);

    @Autowired
    BorrowingRepository borrowingRepo;

    @Autowired
    BookFeignClient bookFeignClient;

    //get all borrowings
    @RequestMapping("/borrowings")
    List<Borrowing> getAllBorrowings(){
        log.info("getAllBorrowings called");
        List<Borrowing> borrowingList = borrowingRepo.findAll();

        for (Borrowing borrowing : borrowingList) {
            Long bookId = borrowing.getBookId();
            Book book = bookFeignClient.getBookById(bookId);
            borrowing.setBookInfo(book);
        }

        return borrowingList;
    }


    @RequestMapping("/borrowings/{borrowingId}")
    Optional<Borrowing> getBorrowingById(@PathVariable("borrowingId") Long borrowingId) {
        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
        if(borrowing.isPresent()){
            Long bookId = borrowing.get().getBookId();
            Book book = bookFeignClient.getBookById(bookId);
            borrowing.get().setBookInfo(book);
            return borrowing;
        } else {
            throw new BorrowingNotFoundException("No borrowing with id: "+borrowingId);
        }
    }

    //get borrowings by books
    @RequestMapping("/borrowings/books/{id}")
    List<Borrowing>findBorrowingsByBookId(@PathVariable("id") Long bookId) {
        List<Borrowing> borrowingList = borrowingRepo.findByBookId(bookId);
        if(!borrowingList.isEmpty()){
            for (Borrowing borrowing : borrowingList) {
                Long borrowingBookId = borrowing.getBookId();
                Book book = bookFeignClient.getBookById(borrowingBookId);
                borrowing.setBookInfo(book);
            }
            return borrowingList;
        } else {
            throw new BorrowingNotFoundException("No borrowing found with book Id: "+ bookId);
        }
    }

    // create borrowing
    @PostMapping("/borrowings")
    ResponseEntity<Borrowing> createBorrowing(@Valid @RequestBody Borrowing borrowing) {
        Borrowing savedBorrowing=borrowingRepo.save(borrowing);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{borrowingId}")
                .buildAndExpand(savedBorrowing.getBorrowingId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // update borrowing
    @PutMapping("borrowings/{borrowingId}")
    public Borrowing updateBorrowing(@Valid @RequestBody Borrowing borrowing, @PathVariable("borrowingId") Long borrowingId)
    {
        Optional<Borrowing> savedBorrowing = borrowingRepo.findById(borrowingId);
        if(savedBorrowing.isPresent()){
            Borrowing existingBorrowing = savedBorrowing.get();
            existingBorrowing.setEndDate(borrowing.getEndDate());
            existingBorrowing.setStatus(borrowing.getStatus());

            borrowingRepo.save(existingBorrowing);
            return existingBorrowing;
        } else{
            throw new BorrowingNotFoundException("No borrowing with id: "+borrowingId);
        }
    }

    // delete borrowing
    @DeleteMapping("/borrowings/{borrowingId}")
    void deleteBorrowingById(@PathVariable("borrowingId") Long borrowingId) {
        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
        if(borrowing.isPresent()){
            Borrowing existingBorrowing = borrowing.get();
            borrowingRepo.delete(existingBorrowing);
        } else {
            throw new BorrowingNotFoundException("No borrowing with id: "+borrowingId);
        }
    }
}
