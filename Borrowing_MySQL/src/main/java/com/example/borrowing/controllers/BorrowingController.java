package com.example.borrowing.controllers;

import com.example.borrowing.dao.BorrowingRepository;
import com.example.borrowing.dto.Book;
import com.example.borrowing.dto.Borrowing;
import com.example.borrowing.dto.ResponseTemplateVo;
import com.example.borrowing.dto.User;
import com.example.borrowing.exception.BorrowingNotFoundException;
import com.example.borrowing.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@Service
public class BorrowingController {

    @Autowired
    BorrowingRepository borrowingRepo;

    @Autowired
    private RestTemplate restTemplate;

    //get all borrowings with pagination options, and sort option
    @RequestMapping("/borrowings")
    Page<Borrowing> sortAllBorrowings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "borrowingId") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).and(Sort.by("borrowingId")));
        Page<Borrowing> borrowingList = borrowingRepo.findAll(pageable);
        return borrowingList;
    }


    @RequestMapping("/borrowings/{id}")
    public ResponseTemplateVo getBorrowingById(@PathVariable("id") Long borrowingId) {
        ResponseTemplateVo vo = new ResponseTemplateVo();
        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
        if(borrowing.isPresent()){
            Borrowing borrowing1 = borrowingRepo.findByBorrowingId(borrowingId);

            Book book = restTemplate.getForObject("http://BOOK-CATALOGUE-SERVICE/books/" + borrowing1.getBookId(), Book.class );

            vo.setBorrowing(borrowing1);
            vo.setBook(book);

            return vo;
        } else {
            throw new BorrowingNotFoundException("No borrowing with id: "+borrowingId);
        }
    }

    //get borrowings by user
    @RequestMapping("/borrowings/users/{id}")
    Page<Borrowing>findBorrowingsByUserId(@PathVariable("id") Long userId ,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Borrowing> borrowingList = borrowingRepo.findByUserId(userId, pageable);
        if(!borrowingList.isEmpty()){
            return borrowingList;
        } else {
            throw new BorrowingNotFoundException("No borrowing found with user Id: "+ userId);
        }
    }

    //get borrowings by books
    @RequestMapping("/borrowings/books/{id}")
    Page<Borrowing>findBorrowingsByBookId(@PathVariable("id") Long bookId ,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Borrowing> borrowingList = borrowingRepo.findByBookId(bookId, pageable);
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
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBorrowing.getBorrowingId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // update borrowing
    @PutMapping("borrowings/{id}")
    public Borrowing updateBorrowing(@Valid @RequestBody Borrowing borrowing, @PathVariable("id") Long borrowingId)
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
    @DeleteMapping("/borrowings/{id}")
    void deleteBorrowingById(@PathVariable("id") Long borrowingId) {
        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
        if(borrowing.isPresent()){
            Borrowing existingBorrowing = borrowing.get();
            borrowingRepo.delete(existingBorrowing);
        } else {
            throw new BorrowingNotFoundException("No borrowing with id: "+borrowingId);
        }
    }
}
