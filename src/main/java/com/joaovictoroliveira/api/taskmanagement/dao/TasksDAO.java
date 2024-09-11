package com.joaovictoroliveira.api.taskmanagement.dao;

import com.joaovictoroliveira.api.taskmanagement.models.TaskModel;
import com.joaovictoroliveira.api.security.models.UserModel;
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
