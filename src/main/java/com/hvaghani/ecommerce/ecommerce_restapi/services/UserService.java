package com.hvaghani.ecommerce.ecommerce_restapi.services;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.SignUpDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.UserDto;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.User;

import java.util.List;

public interface UserService {
    UserDto findById(Long id);

    UserDto findByEmail(String email);

    UserDto createUser(SignUpDto user);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    List<UserDto> getAllUsers();

}
