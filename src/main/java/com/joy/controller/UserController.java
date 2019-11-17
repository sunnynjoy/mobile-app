package com.joy.controller;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(defaultValue = "1", required = false) final Integer page,
                           @RequestParam(defaultValue = "20", required = false) final Integer limit) {
        return limit + " Users are fetched from page " + page;
    }

    @GetMapping(path = "/{userId}")
    public String getAUser(@PathVariable final String userId) {
        return userId;
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public String createAUser(@RequestBody final String user) {
        return "A new user " + user + " is created!";
    }

    @PutMapping
    public String updateAUser(@RequestBody final String user) {
        return "The user " + user + " is updated!";
    }

    @DeleteMapping
    @ResponseStatus(code = NO_CONTENT)
    public String deleteAUser() {
        return "The user is deleted!";
    }
}
