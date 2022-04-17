package com.library.controllers;

import com.library.dao.BookRepository;
import com.library.exception.BookNotFoundException;
import com.library.dto.Book;

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
@Tag(name = "Book")
public class BookService {

    Logger log = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRepository bookRepo;

    //get all books
    @RequestMapping("/books")
    @Operation(summary = "Get all books.", responses = {
            @ApiResponse(description = "Get all books success.", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    })
    List<Book> getAllBooks() {
        log.info("getAllBooks called");
        List<Book> bookList = bookRepo.findAll();
        return bookList;
    }

    // get book by id
    @RequestMapping("/books/{id}")
    @Operation(summary = "Get a book by it's id.", responses = {
            @ApiResponse(description = "Get a book by it's id success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Book not found", responseCode = "404", content = @Content)
    })
    Optional<Book> getBookById(@PathVariable("id") Long id) {
        log.info("getBookById called");
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()){
            return book;
        } else {
            throw new BookNotFoundException("No book with id: "+id);
        }
    }

    //get books by author
    @RequestMapping("/books/authors/{author}")
    @Operation(summary = "Get a book by it's author.", responses = {
            @ApiResponse(description = "Get a book by it's author success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Book not found", responseCode = "404", content = @Content)
    })
    List<Book>findBooksByAuthor(@PathVariable("author") String author) {
        log.info("findBooksByAuthor called");
        List<Book> bookList = bookRepo.findByAuthor(author);
        if(!bookList.isEmpty()){
            return bookList;
        } else {
            throw new BookNotFoundException("No book found from the author: "+author);
        }
    }

    //get books by title
    @RequestMapping("/books/titles/{title}")
    @Operation(summary = "Get a book by it's title.", responses = {
            @ApiResponse(description = "Get a book by it's title success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Book not found", responseCode = "404", content = @Content)
    })
    List<Book>findBooksByTitle(@PathVariable("title") String title) {
        log.info("findBooksByTitle called");
        List<Book> bookList = bookRepo.findByTitle(title);
        if(!bookList.isEmpty()){
            return bookList;
        } else {
            throw new BookNotFoundException("No book found with the title: "+title);
        }
    }

    //get books by ISBN number
    @RequestMapping("/books/isbn-numbers/{isbn}")
    @Operation(summary = "Get a book by it's ISBN number.", responses = {
            @ApiResponse(description = "Get a book by it's ISBN number success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Book not found", responseCode = "404", content = @Content)
    })
    List<Book>findBookByIsbn(@PathVariable("isbn") String isbn) {
        log.info("findBookByIsbn called");
        List<Book> bookList = bookRepo.findByIsbn(isbn);
        if(!bookList.isEmpty()){
            return bookList;
        } else {
            throw new BookNotFoundException("No book found with ISBN number: "+isbn);
        }
    }

    // create book
    @PostMapping("/books")
    @Operation(summary = "Create a new book.", responses = {
            @ApiResponse(description = "Create a new book success.", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    })
    ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        log.info("createBook called");
        Book savedBook=bookRepo.save(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBook.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // update book
    @PutMapping("books/{id}")
    @Operation(summary = "Update a book by it's id.", responses = {
            @ApiResponse(description = "Update a book by it's id success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Book not found", responseCode = "404", content = @Content)
    })
    public Book updateBook(@Valid @RequestBody Book book, @PathVariable("id") Long id)
    {
        log.info("updateBook called");
        Optional<Book> savedBook = bookRepo.findById(id);
        if(savedBook.isPresent()){
            Book existingBook = savedBook.get();
            existingBook.setIsAvailable(book.getIsAvailable());
            bookRepo.save(existingBook);
            return existingBook;
        } else{
            throw new BookNotFoundException("No book found with id: "+id);
        }
    }

    // delete book
    @DeleteMapping("/books/{id}")
    @Operation(summary = "Delete a book by it's id.", responses = {
            @ApiResponse(description = "Delete a book by it's id. success.", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(description = "Book not found", responseCode = "404", content = @Content)
    })
    void deleteBookById(@PathVariable Long id) {
        log.info("deleteBookById called");
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent()){
            Book existingBook = book.get();
            bookRepo.delete(existingBook);
        } else {
            throw new BookNotFoundException("No book found with id: "+id);
        }
    }
}
