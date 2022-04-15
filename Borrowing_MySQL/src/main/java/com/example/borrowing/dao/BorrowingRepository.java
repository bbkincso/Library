package com.example.borrowing.dao;

import com.example.borrowing.dto.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
//    List<Borrowing> findAll();
    List<Borrowing> findByBookId(Long bookId);
//    List<Borrowing> findBorrowingByUser(Long userId);
//    Borrowing findBorrowingById(Long id);
}
