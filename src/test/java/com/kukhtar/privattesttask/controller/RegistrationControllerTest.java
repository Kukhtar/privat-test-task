package com.kukhtar.privattesttask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenAllFieldsValid_successStatus() throws Exception {
        String validUserJSONString = "{\"username\" : \"testUser\", \"password\" : \"password\",\"email\" : \"test@mail\"}";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUserJSONString)).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void whenNotParsableJSON_thenErrorMessage() throws Exception {
        String emptyRequest = "";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("incorrect body")).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Request body is not valid"));

    }

    @Test
    void whenContentIsEmpty_thenErrorMessage() throws Exception {
        String emptyRequest = "";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Request body is not valid"));

    }

    @Test
    void whenNotValidEmail_thenValidationErrorMessage() throws Exception {
        String invalidUserJSONString = "{\"username\" : \"testUser\", \"password\" : \"password\",\"email\" : \"invalid email\"}";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJSONString)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is not valid"));

    }

    @Test
    void whenNotValidPassword_thenValidationErrorMessage() throws Exception {
        String invalidUserJSONString = "{\"username\" : \"testUser\", \"password\" : \"pass\",\"email\" : \"test@email.com\"}";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJSONString)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Password should be at least 8 characters long"));

    }

    @Test
    void whenCoupleValidationErrors_thenListAllValidationErrorMessages() throws Exception {
        String invalidUserJSONString = "{\"username\" : \"testUser\", \"password\" : \"pass\",\"email\" : \"email.com\"}";

        this.mockMvc.perform(post("/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJSONString)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Password should be at least 8 characters long, Email is not valid"));

    }
}