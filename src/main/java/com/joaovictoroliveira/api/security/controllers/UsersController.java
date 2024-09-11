package com.joaovictoroliveira.api.security.controllers;

import com.joaovictoroliveira.api.security.dto.CreateUserDTO;
import com.joaovictoroliveira.api.security.dto.TokenResponseDTO;
import com.joaovictoroliveira.api.security.dto.UserLoginDTO;
import com.joaovictoroliveira.api.security.services.TokenService;
import com.joaovictoroliveira.api.security.services.UsersService;
import com.joaovictoroliveira.api.security.models.UserModel;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;
    @Resource
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password()));
        return ResponseEntity.ok().body(new TokenResponseDTO(tokenService.generateToken((UserModel) auth.getPrincipal())));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO createUserDTO) {
        usersService.createUser(createUserDTO);
        return ResponseEntity.ok().build();
    }
}
