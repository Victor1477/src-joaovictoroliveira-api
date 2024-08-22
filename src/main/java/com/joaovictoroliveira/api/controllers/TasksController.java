package com.joaovictoroliveira.api.controllers;

import com.joaovictoroliveira.api.models.TaskModel;
import com.joaovictoroliveira.api.services.TasksService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Resource
    private TasksService tasksService;

    @GetMapping
    public ResponseEntity getAllTasks() {
        return ResponseEntity.ok(tasksService.getAll());
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskModel taskModel) {
        return ResponseEntity.ok(tasksService.save(taskModel));
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestBody TaskModel taskModel) {
        return ResponseEntity.ok(tasksService.update(taskModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        tasksService.delete(id);
        return ResponseEntity.ok().build();
    }
}
