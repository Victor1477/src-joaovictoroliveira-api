package com.joaovictoroliveira.api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String code;
    @Column
    private String description;
    @Column
    private String featureFlagName;
    @Column
    private String notes;
    @Column
    private String pendencies;
    @Column
    private Boolean isActive;
    @Column
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private List<AttachmentModel> attachments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserModel user;

    public TaskModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatureFlagName() {
        return featureFlagName;
    }

    public void setFeatureFlagName(String featureFlagName) {
        this.featureFlagName = featureFlagName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPendencies() {
        return pendencies;
    }

    public void setPendencies(String pendencies) {
        this.pendencies = pendencies;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


}
