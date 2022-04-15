package com.example.borrowing.feignclients;

import com.example.borrowing.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("zuul-api-gateway")
public interface BookFeignClient {

    @GetMapping("book-catalogue-service/books")
    List<Book> getAllBooks();

    @GetMapping("book-catalogue-service/books/{bookId}")
    Book getBookById(@PathVariable("bookId") Long bookId);
}
