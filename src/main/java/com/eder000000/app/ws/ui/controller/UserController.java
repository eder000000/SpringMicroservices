package com.eder000000.app.ws.ui.controller;

import model.request.UpdateUserDetailsRequestModel;
import model.request.UserDetailsRequestModel;
import model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    Map<String, UserRest> userRestMap;

    @GetMapping()
    public String getUsers(@RequestParam(value ="page", defaultValue ="1") int page,
                           @RequestParam(value ="limit", defaultValue ="50") int limit,
                           @RequestParam(value ="sort", defaultValue = "desc", required = false) String sort){
        return "get user was called with page=" + page + " and limits= " + limit + "and sort = " + sort;
    }
    @GetMapping(path = "/{userId}", produces = {
                                    MediaType.APPLICATION_XML_VALUE,
                                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){
        String firstName = null;
        int firstNameLength = firstName.length();
       if(userRestMap.containsKey(userId)){
           return new ResponseEntity<>(userRestMap.get(userId), HttpStatus.OK);
       } else{
           return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel){
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetailsRequestModel.getEmail());
        returnValue.setFirstName(userDetailsRequestModel.getFirstName());
        returnValue.setLastName(userDetailsRequestModel.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);

        if(userRestMap == null) userRestMap = new HashMap<>();
        userRestMap.put(userId, returnValue);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId,@Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel ){
        UserRest storeUserDetails = userRestMap.get(userId);
        storeUserDetails.setFirstName((updateUserDetailsRequestModel.getFirstName()));
        storeUserDetails.setLastName(updateUserDetailsRequestModel.getLastName());
        userRestMap.put(userId, storeUserDetails);
        return storeUserDetails;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userRestMap.remove(id);
        return ResponseEntity.noContent().build();
    }

}
