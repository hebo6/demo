package com.example.demo.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<User> insertUsers(List<User> users) {
        List<User> savedUsers = userRepository.saveAll(users);
        for (User e : savedUsers) {
            if ("bad".equals(e.getName())) {
                throw new RuntimeException("it contained one more bad name");
            }
        }
        return savedUsers;
    }

    public User findUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }
}
