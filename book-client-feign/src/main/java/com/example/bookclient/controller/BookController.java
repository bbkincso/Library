package com.example.bookclient.controller;

import com.example.bookclient.dto.Book;
import com.example.bookclient.feignclients.BookCatalogueFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookCatalogueFeignClient bookCatalogueFeignClient;

    @RequestMapping("/book-client")
    List<Book>getAllBooks() {
        return bookCatalogueFeignClient.getAllBooks();
    }

    @RequestMapping(value="/book-client/{bookId}", method= RequestMethod.GET)
    public Book borrowSomeBook(@PathVariable(value = "bookId") Long bookId) {
        return bookCatalogueFeignClient.getBookById(bookId);
    }

//    //get books by author
    @RequestMapping("/book-client/authors/{author}")
    List<Book>findBooksByAuthor(@PathVariable("author") String author) {
        return bookCatalogueFeignClient.findBooksByAuthor(author);
    }

    //get books by title
    @RequestMapping("/book-client/titles/{title}")
    List<Book>findBooksByTitle(@PathVariable("title") String title) {
        return bookCatalogueFeignClient.findBooksByTitle(title);
    }

//    //get books by ISBN number
    @RequestMapping("/book-client/isbn-numbers/{isbn}")
    List<Book>findBookByIsbn(@PathVariable("isbn") String isbn) {
        return bookCatalogueFeignClient.findBooksByISBN(isbn);
    }

    @PostMapping("/book-client")
    ResponseEntity<Book> createBook(@RequestBody Book book) {
        return bookCatalogueFeignClient.saveBook(book);
    }

    @PutMapping("/book-client/{bookId}")
    public Book updateBook( @RequestBody Book book, @PathVariable("bookId") Long bookId)
    {
        return bookCatalogueFeignClient.updateBookById(book, bookId);
    }

    @DeleteMapping("/book-client/{bookId}")
    ResponseEntity<Book> deleteBookById(@PathVariable Long bookId) {
        return bookCatalogueFeignClient.deleteBookById(bookId);
    }
}
