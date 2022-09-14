package com.eder000000.app.ws.implementations;

import model.request.UserDetailsRequestModel;
import model.response.UserRest;
import org.springframework.stereotype.Service;
import com.eder000000.app.ws.userService.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {
    Map<String, UserRest> userRestMap;

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetailsRequestModel.getEmail());
        returnValue.setFirstName(userDetailsRequestModel.getFirstName());
        returnValue.setLastName(userDetailsRequestModel.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);

        if(userRestMap == null) userRestMap = new HashMap<>();
        userRestMap.put(userId, returnValue);
        return  returnValue;
    }
}
