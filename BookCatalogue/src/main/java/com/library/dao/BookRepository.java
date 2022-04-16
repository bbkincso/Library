package com.library.dao;

import com.library.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByIsbn(String isbn);
}
