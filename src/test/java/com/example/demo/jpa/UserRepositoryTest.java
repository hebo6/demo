package com.example.demo.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 优势: 使用的是内存中的临时数据库, 并不是读取application.yml配置的数据库
 */
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