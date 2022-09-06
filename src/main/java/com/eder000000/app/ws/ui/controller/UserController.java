package com.eder000000.app.ws.ui.controller;

import model.response.UserRest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    @GetMapping()
    public String getUsers(@RequestParam(value ="page", defaultValue ="1") int page,
                           @RequestParam(value ="limit", defaultValue ="50") int limit,
                           @RequestParam(value ="sort", defaultValue = "desc", required = false) String sort){
        return "get user was called with page=" + page + " and limits= " + limit + "and sort = " + sort;
    }
    @GetMapping(path = "/{userId}", produces = {
                                    MediaType.APPLICATION_XML_VALUE,
                                    MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String userId){
        UserRest returnValue = new UserRest();
        returnValue.setEmail("test@test.com");
        returnValue.setFirstName("Alice");
        returnValue.setLastName("Wonderland");

        return returnValue;
    }

    @PostMapping
    public String createUser(){
        return "create user was called";
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }

}
