package com.joy.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserDtoControllerTest {

    private static final String USERS_URI = "/users/";
    private static final String FILE_PATH_JSON = "src/test/resources/json/user.json";
    private static final String INVALID_FILE_PATH_JSON = "src/test/resources/json/user-invalid.json";
    private static final String FILE_PATH_XML = "src/test/resources/xml/user.xml";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetUsersByPassingPageAndLimit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URI + "?page=1&limit=10"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("10 Users are fetched from page 1"));
    }

    @Test
    public void shouldGetUsersWithPageAndWithoutLimit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URI + "?page=1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("20 Users are fetched from page 1"));
    }

    @Test
    public void shouldGetUsersWithLimitAndWithoutPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URI + "?limit=10"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("10 Users are fetched from page 1"));
    }

    @Test
    public void shouldGetUsersWithOutLimitAndWithoutPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URI))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("20 Users are fetched from page 1"));
    }

    @Test
    public void shouldThrowNotFoundWhenAnInvalidURIIsCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("invalidUri/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetAUser() throws Exception {
        final String content = getJsonString();
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URI + "/sunnyg"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(content));
    }

    @Test
    public void shouldGetAUserWithXmlValue() throws Exception {
        final String content = getXmlString();
        mockMvc.perform(MockMvcRequestBuilders.get(USERS_URI + "/sunnyg").accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(content));
    }

    @Test
    public void shouldAddAUser() throws Exception {
        final String content = getJsonString();
        mockMvc.perform(MockMvcRequestBuilders.post(USERS_URI)
                .contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isCreated())
                .andExpect(content().string("A new user Sunny is created!"));
    }

    @Test
    public void shouldThrowValidationErrorWhenFirstNameIsEmpty() throws Exception {
        final String content = getInvalidJsonString();
        mockMvc.perform(MockMvcRequestBuilders.post(USERS_URI)
                .contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(status().isBadRequest());
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

    private String getJsonString() throws IOException {
        return new String(readAllBytes(Paths.get(FILE_PATH_JSON)))
                .replaceAll("\n", "").replaceAll(" ", "");
    }

    private String getInvalidJsonString() throws IOException {
        return new String(readAllBytes(Paths.get(INVALID_FILE_PATH_JSON)));
    }

    private String getXmlString() throws IOException {
        return new String(readAllBytes(Paths.get(FILE_PATH_XML)))
                .replaceAll("\n", "").replaceAll(" ", "");
    }
}
