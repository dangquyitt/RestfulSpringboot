package com.junior.Restful.controller;

import com.junior.Restful.entity.User;
import com.junior.Restful.model.dto.UserDTO;
import com.junior.Restful.model.request.UserDetailsRequestModel;
import com.junior.Restful.model.response.UserRest;
import com.junior.Restful.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser() {
        return "Get user was called";
    }

    @PostMapping
    public UserRest  User(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);

        UserDTO createUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createUser, returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        return "Update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete user was called";
    }
}
