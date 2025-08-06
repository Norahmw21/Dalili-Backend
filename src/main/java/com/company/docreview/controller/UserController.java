package com.company.docreview.controller;

import com.company.docreview.dto.LoginRequestDTO;
import com.company.docreview.entity.User;
import com.company.docreview.service.UserService;
import org.springframework.http.ResponseEntity;
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
    // POST /api/users/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        Optional<User> user = service.getUserByEmailAndPassword(request.getEmail(), request.getPassword());

        if (user.isPresent()) {
            User safeUser = user.get();
            safeUser.setPassword(null); // hide password in response
            return ResponseEntity.ok(safeUser);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
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
