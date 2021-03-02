package com.example.demo.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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
        doReturn(optional).when(userRepository).findById(1L);
    }

    @ParameterizedTest
    @CsvSource({"1,true", "2,false"})
    void findUserById(Long id, boolean exist) {
        User user = userService.findUserById(id);
        if (exist) {
            assertEquals(mockName, user.getName());
        } else {
            assertNull(user);
        }
    }

    /**
     * mock 被测试类中 的 其它方法
     */
    @Test
    void whoAmI() {
        UserService spyService = Mockito.spy(userService);
        String spyName = "Spy";
        doReturn(new User().setName(spyName)).when(spyService).findUserById(any());
        //为什么下面的语句会报错?
        //因为when/thenReturn会执行findUserById(), 而doReturn/when不会执行
        //总是使用doReturn/when,而不是when/thenReturn, 因为第一种适用所有情况,而第二种不适用与某些情况(如void方法)
        //参考: https://stackoverflow.com/a/20360269
//        when(spyService.findUserById(any())).thenReturn(new User().setName(spyName));
        String name = spyService.whoAmI();
        assertEquals(spyName, name);
    }
}
