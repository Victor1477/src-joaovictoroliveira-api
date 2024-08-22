package com.joaovictoroliveira.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "attachments")
public class AttachmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String fileName;
    @Column
    private String contentType;
    @Lob
    @Column
    private Byte[] fileData;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskModel task;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserModel user;

    public AttachmentModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Byte[] getFileData() {
        return fileData;
    }

    public void setFileData(Byte[] fileData) {
        this.fileData = fileData;
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
