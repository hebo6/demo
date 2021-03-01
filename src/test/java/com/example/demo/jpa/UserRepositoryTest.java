package com.example.demo.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1L);
        userRepository.save(user);
    }

    @Test
    void findById() {
        Optional<User> optional = userRepository.findById(1L);
        assertTrue(optional.isPresent());
    }
}