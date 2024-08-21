package com.joaovictoroliveira.api.services;

import com.joaovictoroliveira.api.dao.UsersDAO;
import com.joaovictoroliveira.api.dto.CreateUserDTO;
import com.joaovictoroliveira.api.exceptions.UserAlreadyExistsException;
import com.joaovictoroliveira.api.models.UserModel;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class UsersService implements UserDetailsService {

    @Resource
    private UsersDAO usersDAO;

    public void createUser(CreateUserDTO createUserDTO) {
        if (usersDAO.findByUsername(createUserDTO.username()) != null) {
            throw new UserAlreadyExistsException();
        }
        UserModel userModel = new UserModel();
        userModel.setUsername(createUserDTO.username());
        userModel.setPassword(encryptPassword(createUserDTO.password()));
        userModel.setRole(createUserDTO.role());
        userModel.setCreatedDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        usersDAO.save(userModel);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersDAO.findByUsername(username);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public UsersDAO getUsersDAO() {
        return usersDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }
}
