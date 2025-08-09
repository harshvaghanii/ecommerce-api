package com.hvaghani.ecommerce.ecommerce_restapi.controllers;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.SignUpDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.UserDto;
import com.hvaghani.ecommerce.ecommerce_restapi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<UserDto> findByEmail(@RequestParam(required = false) String email) {
        if (email.trim() == null) {
            return null;
        }
        UserDto userDto = userService.findByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> signUpUser(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.createUser(signUpDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDto> modifyUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        UserDto user = userService.updateUser(id, userDto);
        return ResponseEntity.ok(user);
    }

}
