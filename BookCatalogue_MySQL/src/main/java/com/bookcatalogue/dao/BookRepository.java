package com.bookcatalogue.dao;

import com.bookcatalogue.dto.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    List<Book> findByTitle(String title);
    List<Book> findByIsbn(String isbn);
}
