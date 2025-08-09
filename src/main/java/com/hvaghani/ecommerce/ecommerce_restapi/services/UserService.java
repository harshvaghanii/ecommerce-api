package com.hvaghani.ecommerce.ecommerce_restapi.services;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.SignUpDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.UserDto;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto findById(Long id);

    UserDto findByEmail(String email);

    UserDto createUser(SignUpDto user);

    UserDto updateUser(User user);

    void deleteUser(Long id);

    List<UserDto> getAllUsers();

}
