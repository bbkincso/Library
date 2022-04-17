package com.library.Borrowing.controllers;

import com.library.Borrowing.dao.BorrowingRepository;
import com.library.Borrowing.dto.Book;
import com.library.Borrowing.dto.Borrowing;
import com.library.Borrowing.exceptions.BorrowingNotFoundException;
import com.library.Borrowing.feignclients.BookFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Borrowing")
public class BorrowingController {

    Logger log = LoggerFactory.getLogger(BorrowingController.class);

    @Autowired
    BorrowingRepository borrowingRepo;

    @Autowired
    BookFeignClient bookFeignClient;

    //get all borrowings
    @RequestMapping("/borrowings")
    @Operation(summary = "Get all borrowings.", responses = {
            @ApiResponse(description = "Get all borrowings success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    })
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

    // get a borrowing by id
    @RequestMapping("/borrowings/{borrowingId}")
    @Operation(summary = "Get a borrowing by it's id.", responses = {
            @ApiResponse(description = "Get a borrowing by it's id success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Borrowing not found", responseCode = "404", content = @Content)
    })
    Optional<Borrowing> getBorrowingById(@PathVariable("borrowingId") Long borrowingId) {
        log.info("getBorrowingById called");
        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
        if(borrowing.isPresent()){
            Long bookId = borrowing.get().getBookId();
            Book book = bookFeignClient.getBookById(bookId);
            borrowing.get().setBookInfo(book);
            return borrowing;
        } else {
            throw new BorrowingNotFoundException("No borrowing found with id: "+borrowingId);
        }
    }

    //get borrowings by book id
    @RequestMapping("/borrowings/books/{id}")
    @Operation(summary = "Get a borrowing by book id.", responses = {
            @ApiResponse(description = "Get a borrowing by book id success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Borrowing not found", responseCode = "404", content = @Content)
    })
    List<Borrowing>findBorrowingsByBookId(@PathVariable("id") Long bookId) {
        log.info("findBorrowingsByBookId called");
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
    @Operation(summary = "Create a new borrowings.", responses = {
            @ApiResponse(description = "Create a new borrowings success.", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    })
    ResponseEntity<Borrowing> createBorrowing(@Valid @RequestBody Borrowing borrowing) {
        log.info("createBorrowing called");
        Borrowing savedBorrowing=borrowingRepo.save(borrowing);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{borrowingId}")
                .buildAndExpand(savedBorrowing.getBorrowingId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // update borrowing
    @PutMapping("borrowings/{borrowingId}")
    @Operation(summary = "Update a borrowing by it's id.", responses = {
            @ApiResponse(description = "Update a borrowing by it's id success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Borrowing not found", responseCode = "404", content = @Content)
    })
    public Borrowing updateBorrowing(@Valid @RequestBody Borrowing borrowing, @PathVariable("borrowingId") Long borrowingId)
    {
        log.info("updateBorrowing called");
        Optional<Borrowing> savedBorrowing = borrowingRepo.findById(borrowingId);
        if(savedBorrowing.isPresent()){
            Borrowing existingBorrowing = savedBorrowing.get();
            existingBorrowing.setEndDate(borrowing.getEndDate());
            existingBorrowing.setStatus(borrowing.getStatus());

            borrowingRepo.save(existingBorrowing);
            return existingBorrowing;
        } else{
            throw new BorrowingNotFoundException("No borrowing found with id: "+borrowingId);
        }
    }

    // delete borrowing
    @DeleteMapping("/borrowings/{borrowingId}")
    @Operation(summary = "Delete a borrowing by it's id.", responses = {
            @ApiResponse(description = "Delete a borrowing by it's id success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Borrowing not found", responseCode = "404", content = @Content)
    })
    void deleteBorrowingById(@PathVariable("borrowingId") Long borrowingId) {
        log.info("deleteBorrowingById called");
        Optional<Borrowing> borrowing = borrowingRepo.findById(borrowingId);
        if(borrowing.isPresent()){
            Borrowing existingBorrowing = borrowing.get();
            borrowingRepo.delete(existingBorrowing);
        } else {
            throw new BorrowingNotFoundException("No borrowing found with id: "+borrowingId);
        }
    }
}
