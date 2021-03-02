package com.example.demo.security;

import com.example.demo.jpa.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SecurityController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockBean(UserService.class)
class SecurityControllerWithoutFiltersTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void addResources() throws Exception {
        this.mockMvc.perform(post("/security")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }
}
