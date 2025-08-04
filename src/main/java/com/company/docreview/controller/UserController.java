package com.company.docreview.controller;

import com.company.docreview.entity.User;
import com.company.docreview.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // GET user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // POST create new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    // PUT update existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
