package com.example.demo.security;

import com.example.demo.jpa.User;
import com.example.demo.jpa.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SecurityController.class)
@AutoConfigureMockMvc(addFilters = false)
class SecurityControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private UserService service;
    private final String mockName = "Mock";

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setName(mockName);
        when(service.findUserById(1L)).thenReturn(user);
    }

    @Test
    void findResources() throws Exception {
        this.mockMvc.perform(get("/security")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Hello, " + mockName + ". You are now accessing secure resources"));
    }
}
