package com.joaovictoroliveira.api.dto;

import com.joaovictoroliveira.api.enums.Role;

public record CreateUserDTO(String username, String password, Role role) {
}
