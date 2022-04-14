package com.example.borrowing.dao;

import com.example.borrowing.dto.Borrowing;
import com.example.borrowing.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Page<Borrowing> findAll(Pageable pageable);
}
