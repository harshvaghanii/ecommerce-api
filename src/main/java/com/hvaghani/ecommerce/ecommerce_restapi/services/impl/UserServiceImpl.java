package com.hvaghani.ecommerce.ecommerce_restapi.services.impl;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.SignUpDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.UserDto;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.User;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.enums.Role;
import com.hvaghani.ecommerce.ecommerce_restapi.exceptions.ResourceNotFoundException;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.UserRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.services.UserService;
import com.hvaghani.ecommerce.ecommerce_restapi.services.utilities.PropertyCopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + id + " not found!"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email: " + email + " not found!"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto createUser(SignUpDto user) {
        User toSave = modelMapper.map(user, User.class);
        toSave.setLastEditedAt(LocalDateTime.now());
        toSave.setRoles(Set.of(Role.CUSTOMER));
        User savedUser = userRepository.save(toSave);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {

        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null for update.");
        }

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found!"));

        User sourceUser = modelMapper.map(userDto, User.class);
        PropertyCopyUtils.copyNonNullProperties(sourceUser, existingUser);
        existingUser.setLastEditedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream().
                map((element) -> modelMapper.map(element, UserDto.class))
                .toList();
    }
}
