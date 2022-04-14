package com.example.user.controllers;

import com.example.user.dao.UserRepository;
import com.example.user.dto.User;
import com.example.user.exception.UserNotFoundException;
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
public class UserService {

    @Autowired
    UserRepository userRepo;

    //get all users with pagination options, and sort option
    @RequestMapping("/users")
    Page<User> sortAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).and(Sort.by("id")));
        Page<User> userList = userRepo.findAll(pageable);
        return userList;
    }

    // get user by id
    @RequestMapping("/users/{id}")
    Optional<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user;
        } else {
            throw new UserNotFoundException("No user with id: "+id);
        }
    }

    //get user by lastname
    @RequestMapping("/users/lastName/{lastName}")
    Page<User>findUsersByLastName(@PathVariable("lastName") String lastName ,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userList = userRepo.findByLastName(lastName, pageable);
        if(!userList.isEmpty()){
            return userList;
        } else {
            throw new UserNotFoundException("No user found with lastname: "+ lastName);
        }
    }

    //get users by email
    @RequestMapping("/users/email/{email}")
    Optional<User> findUserByEmail(@PathVariable("email") String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            return user;
        } else {
            throw new UserNotFoundException("No user found with the email address: "+email);
        }
    }

    // create user
    @PostMapping("/users")
    ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser=userRepo.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // update user
    @PutMapping("users/{id}")
    public User updateUser(@Valid @RequestBody User user, @PathVariable("id") Long id)
    {
        Optional<User> savedBook = userRepo.findById(id);
        if(savedBook.isPresent()){
            User existingUser = savedBook.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setDob(user.getDob());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setAddress(user.getAddress());

            userRepo.save(existingUser);
            return existingUser;
        } else{
            throw new UserNotFoundException("No user with id: "+id);
        }
    }

    // delete user
    @DeleteMapping("/users/{id}")
    void deleteUserById(@PathVariable Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            User existingUser = user.get();
            userRepo.delete(existingUser);
        } else {
            throw new UserNotFoundException("No user with id: "+id);
        }
    }
}
