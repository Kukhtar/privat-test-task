package com.kukhtar.privattesttask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void validRegistrationRequestTest() throws Exception {
        String validUserJSONString = "{" +
                "\"username\" : \"testUser\", " +
                "\"password\" : \"password\"," +
                "\"email\" : \"test@mail\"" +
                "}";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUserJSONString)).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void invalidEmailRegistrationRequestTest() throws Exception {
        String invalidUserJSONString = "{" +
                "\"username\" : \"testUser\", " +
                "\"password\" : \"password\"," +
                "\"email\" : \"invalid email\"" +
                "}";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJSONString)).andDo(print())
                .andExpect(status().isBadRequest());

    }
}