package com.hvaghani.ecommerce.ecommerce_restapi.dto;

import com.hvaghani.ecommerce.ecommerce_restapi.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    Set<Role> roles;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime lastEditedAt;

}
