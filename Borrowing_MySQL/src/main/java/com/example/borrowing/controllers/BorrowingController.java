package com.example.borrowing.controllers;

import com.example.borrowing.dao.BorrowingRepository;
import com.example.borrowing.dto.Borrowing;
import com.example.borrowing.exception.BorrowingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Service
public class BorrowingController {

    @Autowired
    BorrowingRepository borrowingRepo;

    //get all borrowings
    @RequestMapping("/borrowings")
    List<Borrowing> getAllBorrowings(){
        List<Borrowing> borrowingList = borrowingRepo.findAll();
        return borrowingList;
    }


    @RequestMapping("/borrowings/{borrowingId}")
    Optional<Borrowing> getBorrowingById(@PathVariable("borrowingId") Long borrowingId) {
        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
        if(borrowing.isPresent()){
            return borrowing;
        } else {
            throw new BorrowingNotFoundException("No borrowing with id: "+borrowingId);
        }
    }


    //get borrowings by user
//    @RequestMapping("/borrowings/users/{userId}")
//    List<Borrowing> findBorrowingsByUserId(@PathVariable("userId") Long userId) {
//        List<Borrowing> borrowingList = borrowingRepo.findBorrowingByUser(userId);
//        if(!borrowingList.isEmpty()){
//            return borrowingList;
//        } else {
//            throw new BorrowingNotFoundException("No borrowing found with user Id: "+ userId);
//        }
//    }

    //get borrowings by books
    @RequestMapping("/borrowings/books/{id}")
    List<Borrowing>findBorrowingsByBookId(@PathVariable("id") Long bookId) {
        List<Borrowing> borrowingList = borrowingRepo.findByBookId(bookId);
        if(!borrowingList.isEmpty()){
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
