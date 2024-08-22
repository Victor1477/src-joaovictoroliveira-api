package com.joaovictoroliveira.api.services;

import com.joaovictoroliveira.api.dao.TasksDAO;
import com.joaovictoroliveira.api.exceptions.TaskNotFoundException;
import com.joaovictoroliveira.api.models.TaskModel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

@Service
public class TasksService {

    @Resource
    private TasksDAO tasksDAO;
    @Resource
    private UsersService usersService;

    public List<TaskModel> getAll() {
        return tasksDAO.findAllByUser(usersService.getCurrentUser())
                .stream().sorted(Comparator.comparing(TaskModel::getCreatedDate, Comparator.reverseOrder())).toList();
    }

    public TaskModel save(TaskModel taskModel) {
        taskModel.setId(null);
        taskModel.setUser(usersService.getCurrentUser());
        taskModel.setCreatedDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return tasksDAO.save(taskModel);
    }

    public TaskModel update(TaskModel taskModel) {
        TaskModel currentTaskModel = tasksDAO.findByIdAndUser(taskModel.getId(), usersService.getCurrentUser());
        if (currentTaskModel == null) {
            throw new TaskNotFoundException();
        }
        if (!taskModel.getIsActive()) {
            taskModel.setPendencies(null);
        }
        taskModel.setUser(usersService.getCurrentUser());
        taskModel.setCreatedDate(currentTaskModel.getCreatedDate());
        return tasksDAO.save(taskModel);
    }

    public void delete(Integer id) {
        TaskModel taskModel = tasksDAO.findByIdAndUser(id, usersService.getCurrentUser());
        if (taskModel == null) {
            throw new TaskNotFoundException();
        }
        tasksDAO.delete(taskModel);
    }

    public TasksDAO getTasksDAO() {
        return tasksDAO;
    }

    public void setTasksDAO(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
