package com.joaovictoroliveira.api.taskmanagement.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task not found");
    }
}
