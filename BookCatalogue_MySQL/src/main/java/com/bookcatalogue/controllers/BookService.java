package com.bookcatalogue.controllers;

import com.bookcatalogue.dao.BookRepository;


import com.bookcatalogue.dto.Book;
import com.bookcatalogue.exception.BookNotFoundException;
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
import java.util.List;
import java.util.Optional;

@RestController
@Service
public class BookService {

    @Autowired
    BookRepository bookRepo;


	//get all books with pagination options, and sort option
	@RequestMapping("/books")
	Page<Book>sortAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort).and(Sort.by("id")));
		Page<Book> bookList = bookRepo.findAll(pageable);
		return bookList;
	}

	// get book by id
    @RequestMapping("/books/{id}")
    Optional<Book> getBookById(@PathVariable("id") Long id) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isPresent()){
			return book;
		} else {
			throw new BookNotFoundException("No book with id: "+id);
		}
    }

	//get books by author
	@RequestMapping("/books/authors/{author}")
	Page<Book>findBooksByAuthor(@PathVariable("author") String author ,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Book> bookList = bookRepo.findByAuthor(author, pageable);
		if(!bookList.isEmpty()){
			return bookList;
		} else {
			throw new BookNotFoundException("No book found from the author: "+author);
		}
	}

	//get books by title
	@RequestMapping("/books/titles/{title}")
	List<Book>findBooksByTitle(@PathVariable("title") String title) {
		List<Book> bookList = bookRepo.findByTitle(title);
		if(!bookList.isEmpty()){
			return bookList;
		} else {
			throw new BookNotFoundException("No book found with the title: "+title);
		}
	}

	//get books by ISBN number
	@RequestMapping("/books/isbn/{isbn}")
	List<Book>findBookByIsbn(@PathVariable("isbn") String isbn) {
		List<Book> bookList = bookRepo.findByIsbn(isbn);
		if(!bookList.isEmpty()){
			return bookList;
		} else {
			throw new BookNotFoundException("No book found with ISBN number: "+isbn);
		}
	}

    @PostMapping("/books")
    ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook=bookRepo.save(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBook.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

	@PutMapping("books/{id}")
	public Book updateBook(@Valid @RequestBody Book book, @PathVariable("id") Long id)
	{
		Optional<Book> savedBook = bookRepo.findById(id);
		if(savedBook.isPresent()){
			Book existingBook = savedBook.get();
			existingBook.setAuthor(book.getAuthor());
			existingBook.setTitle(book.getTitle());
			existingBook.setIsbn(book.getIsbn());
			existingBook.setIsAvailable(book.getIsAvailable());
			bookRepo.save(existingBook);
			return existingBook;
		} else{
			throw new BookNotFoundException("No book with id: "+id);
		}
	}

    @DeleteMapping("/books/{id}")
	void deleteBookById(@PathVariable Long id) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isPresent()){
			Book existingBook = book.get();
			bookRepo.delete(existingBook);
		} else {
			throw new BookNotFoundException("No book with id: "+id);
		}
	}
}
