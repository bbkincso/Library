package com.example.borrowing.controllers;

import com.example.borrowing.dao.BorrowingRepository;
import com.example.borrowing.dto.Borrowing;
import com.example.borrowing.dto.User;
import com.example.borrowing.exception.BorrowingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@Service
public class BorrowingService {

    @Autowired
    BorrowingRepository borrowingRepo;

    //get all borrowings with pagination options, and sort option
    @RequestMapping("/borrowings")
    Page<Borrowing> sortAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).and(Sort.by("id")));
        Page<Borrowing> borrowingList = borrowingRepo.findAll(pageable);
        return borrowingList;
    }

    // get borrowing by id
    @RequestMapping("/borrowings/{id}")
    Optional<Borrowing> getBorrowingById(@PathVariable("id") Long id) {
        Optional<Borrowing> borrowing = borrowingRepo.findById(id);
        if(borrowing.isPresent()){
            return borrowing;
        } else {
            throw new BorrowingNotFoundException("No borrowing with id: "+id);
        }
    }

    //get borrowings by user

    //get borrowings by books

    // create borrowing
    @PostMapping("/borrowings")
    ResponseEntity<Borrowing> createBorrowing(@Valid @RequestBody Borrowing borrowing) {
        Borrowing savedBorrowing=borrowingRepo.save(borrowing);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBorrowing.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // update borrowing
    @PutMapping("borrowings/{id}")
    public Borrowing updateBorrowing(@Valid @RequestBody Borrowing borrowing, @PathVariable("id") Long id)
    {
        Optional<Borrowing> savedBorrowing = borrowingRepo.findById(id);
        if(savedBorrowing.isPresent()){
            Borrowing existingBorrowing = savedBorrowing.get();
            existingBorrowing.setEndDate(borrowing.getEndDate());
            existingBorrowing.setStatus(borrowing.getStatus());

            borrowingRepo.save(existingBorrowing);
            return existingBorrowing;
        } else{
            throw new BorrowingNotFoundException("No borrowing with id: "+id);
        }
    }

    // delete borrowing
    @DeleteMapping("/borrowings/{id}")
    void deleteBorrowingById(@PathVariable Long id) {
        Optional<Borrowing> borrowing = borrowingRepo.findById(id);
        if(borrowing.isPresent()){
            Borrowing existingBorrowing = borrowing.get();
            borrowingRepo.delete(existingBorrowing);
        } else {
            throw new BorrowingNotFoundException("No borrowing with id: "+id);
        }
    }
}
