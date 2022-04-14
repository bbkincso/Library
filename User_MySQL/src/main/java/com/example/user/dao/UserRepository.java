package com.example.user.dao;

import com.example.user.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    Page<User> findByLastName(String lastName, Pageable pageable);
    Optional<User> findByEmail(String email);
}
