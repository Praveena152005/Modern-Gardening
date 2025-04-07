package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.editUser(id, user.getUsername(), user.getEmail(), user.getOrders());
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/{field}")
    public ResponseEntity<List<User>> sort(@PathVariable String field) {
        List<User> sortedUsers = userService.sort(field);
        return new ResponseEntity<>(sortedUsers, HttpStatus.OK);
    }

    @GetMapping("user/{offset}/{pagesize}")
    public ResponseEntity<List<User>> paginate(@PathVariable int offset, @PathVariable int pagesize) {
        List<User> paginatedUsers = userService.page(pagesize, offset);
        return new ResponseEntity<>(paginatedUsers, HttpStatus.OK);
    }

    @GetMapping("user/{offset}/{pagesize}/{field}")
    public ResponseEntity<List<User>> paginateAndSort(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        List<User> paginatedSortedUsers = userService.pagesort(pagesize, offset, field);
        return new ResponseEntity<>(paginatedSortedUsers, HttpStatus.OK);
    }
}
