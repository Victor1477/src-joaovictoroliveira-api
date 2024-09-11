package com.joaovictoroliveira.api.security.dto;

import com.joaovictoroliveira.api.security.enums.Role;

public record CreateUserDTO(String username, String password, Role role) {
}
