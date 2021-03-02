package com.example.demo.security;

import com.example.demo.jpa.User;
import com.example.demo.jpa.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = SecurityController.class,
        properties = {"spring.security.user.name=123", "spring.security.user.password=456"}
)
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
        when(service.whoAmI()).thenReturn(mockName);
    }

    @Test
    void findResourcesWithoutUser() throws Exception {
        this.mockMvc.perform(get("/security")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    void findResourcesWithUser() throws Exception {
        this.mockMvc.perform(
                get("/security")
                        .with(user("123").password("456"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + mockName + ". You are now accessing secure resources"));
    }

    @Test
    void addResourcesWithoutCsrf() throws Exception {
        this.mockMvc.perform(
                post("/security")
                        .with(user("123").password("456"))
        ).andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void addResourcesWithCsrf() throws Exception {
        this.mockMvc.perform(
                post("/security")
                        .with(user("123").password("456"))
                        .with(csrf())
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }
}
