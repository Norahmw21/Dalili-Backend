package com.company.docreview.service;

import com.company.docreview.entity.User;
import com.company.docreview.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    public Optional<User> getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return repo.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword()); // plain for now
            user.setRole(updatedUser.getRole());
            return repo.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
