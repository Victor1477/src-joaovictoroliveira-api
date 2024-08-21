package com.joaovictoroliveira.api.controllers;

import com.joaovictoroliveira.api.dto.CreateUserDTO;
import com.joaovictoroliveira.api.dto.ErrorHandlerDTO;
import com.joaovictoroliveira.api.dto.TokenResponseDTO;
import com.joaovictoroliveira.api.dto.UserLoginDTO;
import com.joaovictoroliveira.api.exceptions.UserAlreadyExistsException;
import com.joaovictoroliveira.api.models.UserModel;
import com.joaovictoroliveira.api.services.TokenService;
import com.joaovictoroliveira.api.services.UsersService;
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
