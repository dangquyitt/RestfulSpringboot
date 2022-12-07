package com.junior.Restful.service;

import com.junior.Restful.entity.User;
import com.junior.Restful.model.dto.UserDTO;
import com.junior.Restful.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException("Record already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        user.setEncryptedPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserId(UUID.randomUUID().toString());

        User storedUserDetails = userRepository.save(user);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }

    @Override
    public UserDTO getUser(String email) {
        User userRepo = userRepository.findByEmail(email);
        if(userRepo == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDTO returnValue = new UserDTO();

        BeanUtils.copyProperties(
                userRepo, returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());

    }
}
