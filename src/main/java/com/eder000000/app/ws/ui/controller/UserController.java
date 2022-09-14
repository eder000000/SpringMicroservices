package com.eder000000.app.ws.ui.controller;

import com.eder000000.app.ws.exceptions.UserServiceExceptions;
import model.request.UpdateUserDetailsRequestModel;
import model.request.UserDetailsRequestModel;
import model.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eder000000.app.ws.userService.UserService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    Map<String, UserRest> userRestMap;
    @Autowired
    UserService userService;

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

       if(true) throw new UserServiceExceptions("A user service exception is thrown");

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

        UserRest returnValue = userService.createUser(userDetailsRequestModel);
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
