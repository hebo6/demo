package com.example.demo.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserService.class)
@MockBean(UserRepository.class)
class UserServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    String mockName = "Mock";

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setName(mockName);
        Optional<User> optional = Optional.of(user);
        Mockito.doReturn(optional).when(userRepository).findById(1L);
    }

    @Test
    void findUserById() {
        User user = userService.findUserById(1L);
        assertEquals(mockName, user.getName());
    }
}