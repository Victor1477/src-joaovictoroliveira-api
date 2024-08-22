package com.joaovictoroliveira.api.dao;

import com.joaovictoroliveira.api.models.TaskModel;
import com.joaovictoroliveira.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksDAO extends JpaRepository<TaskModel, Integer> {

    @Query("SELECT t FROM TaskModel as t WHERE t.user = :user")
    List<TaskModel> findAllByUser(@Param("user") UserModel user);

    @Query("SELECT t FROM TaskModel as t WHERE t.id = :id AND t.user = :user")
    TaskModel findByIdAndUser(@Param("id") Integer id, @Param("user") UserModel user);
}
