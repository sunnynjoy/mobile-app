package com.joy.controller;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getAUser() {
        return "Sunny";
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
