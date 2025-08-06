package com.company.docreview.controller;

import com.company.docreview.dto.LoginRequestDTO;
import com.company.docreview.dto.UserDTO;
import com.company.docreview.entity.User;
import com.company.docreview.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        Optional<User> userOpt = service.getUserByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("message", "No account found with this email"));
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Incorrect password"));
        }

        UserDTO response = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
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
