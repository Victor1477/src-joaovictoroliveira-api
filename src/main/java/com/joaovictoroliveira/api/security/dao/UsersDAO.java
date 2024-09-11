package com.joaovictoroliveira.api.security.dao;

import com.joaovictoroliveira.api.security.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<UserModel, Integer> {

    public UserModel findByUsername(String username);
}
