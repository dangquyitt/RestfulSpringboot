package com.junior.Restful.service;

import com.junior.Restful.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(String email);

    UserDTO getUserByUserId(String userId);
}
