package com.example.demo.jpa;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public List<User> insertUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public User findUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }
}
