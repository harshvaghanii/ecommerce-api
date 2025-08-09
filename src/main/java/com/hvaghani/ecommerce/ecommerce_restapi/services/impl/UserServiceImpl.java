package com.hvaghani.ecommerce.ecommerce_restapi.services.impl;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.SignUpDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.UserDto;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.User;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.UserRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public UserDto findByEmail(String email) {
        return null;
    }

    @Override
    public UserDto createUser(SignUpDto user) {
        return null;
    }

    @Override
    public UserDto updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }
}
