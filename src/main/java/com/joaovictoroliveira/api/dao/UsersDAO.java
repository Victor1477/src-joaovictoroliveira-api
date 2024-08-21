package com.joaovictoroliveira.api.dao;

import com.joaovictoroliveira.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<UserModel, Integer> {

    public UserModel findByUsername(String username);
}
