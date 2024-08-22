package com.joaovictoroliveira.api.dao;

import com.joaovictoroliveira.api.models.AttachmentModel;
import com.joaovictoroliveira.api.models.TaskModel;
import com.joaovictoroliveira.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentsDAO extends JpaRepository<AttachmentModel, Integer> {

    @Query("SELECT a FROM AttachmentModel as a WHERE a.task = :task AND a.user = :user")
    public List<AttachmentModel> findAllByTaskIdAndUser(@Param("task") TaskModel task, @Param("user") UserModel user);

    @Query("SELECT a FROM AttachmentModel as a WHERE a.id = :id AND a.user = :user")
    public AttachmentModel findByIdAndUser(@Param("id") Integer id, @Param("user") UserModel user);
}
