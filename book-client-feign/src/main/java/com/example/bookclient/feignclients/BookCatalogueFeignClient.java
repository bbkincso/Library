package com.example.bookclient.feignclients;

import com.example.bookclient.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("book-catalogue-service")
public interface BookCatalogueFeignClient {

    @GetMapping("books")
    List<Book>getAllBooks();

    @GetMapping("books/{bookId}")
    Book getBookById(@PathVariable("bookId") Long bookId);

    @GetMapping("books/authors/{author}")
    List<Book> findBooksByAuthor(@PathVariable("author") String author);

    @GetMapping("books/titles/{title}")
    List<Book> findBooksByTitle(@PathVariable("title") String title);

    @GetMapping("books/isbn-numbers/{isbn}")
    List<Book> findBooksByISBN(@PathVariable("isbn") String isbn);

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    ResponseEntity<Book> saveBook(@RequestBody Book book);

    @PutMapping("/books/{bookId}")
    Book updateBookById(@RequestBody Book book, @PathVariable("bookId") Long bookId);

    @DeleteMapping("/books/{bookId}")
    ResponseEntity<Book> deleteBookById(@PathVariable Long bookId);

}
