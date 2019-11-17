package com.joy.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String USERS_URI = "/users/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetAUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URI))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Sunny"));
    }

    @Test
    public void shouldAddAUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USERS_URI).content("Raj"))
                .andExpect(status().isCreated())
                .andExpect(content().string("A new user Raj is created!"));
    }

    @Test
    public void shouldUpdateAUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(USERS_URI).content("Raj"))
                .andExpect(status().isOk())
                .andExpect(content().string("The user Raj is updated!"));
    }

    @Test
    public void shouldDeleteAUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(USERS_URI))
                .andExpect(status().isNoContent())
                .andExpect(content().string("The user is deleted!"));
    }
}
