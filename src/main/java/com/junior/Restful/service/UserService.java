package com.junior.Restful.service;

import com.junior.Restful.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    public UserDTO createUser(UserDTO userDTO);
}
