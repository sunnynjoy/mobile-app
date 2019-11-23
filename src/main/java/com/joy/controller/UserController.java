package com.joy.controller;

import com.joy.dto.UpdateUser;
import com.joy.dto.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(defaultValue = "1", required = false) final Integer page,
                           @RequestParam(defaultValue = "20", required = false) final Integer limit) {
        return limit + " Users are fetched from page " + page;
    }

    @GetMapping(path = "/{userName}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public User getAUser(@PathVariable final String userName) {
        return User.builder().userName(userName).email("sunny.g@email.com")
                .firstName("Sunny")
                .lastName("G").build();
    }

    @PostMapping(consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}, produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    @ResponseStatus(CREATED)
    public String createAUser(@Valid @RequestBody final User user) {
        return "A new user " + user.getFirstName() + " is created!";
    }

    @PutMapping("/{userName}")
    public User updateUserDetails(@PathVariable String userName, @Valid @RequestBody final UpdateUser user) {
        return User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
                .email("pew.g@email.com")
                .userName(userName)
                .build();
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public String deleteAUser() {
        return "The user is deleted!";
    }
}
