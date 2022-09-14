package com.eder000000.app.ws.userService;

import model.request.UserDetailsRequestModel;
import model.response.UserRest;

public interface UserService {

    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);

}
