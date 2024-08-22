package com.joaovictoroliveira.api.services;

import com.joaovictoroliveira.api.dao.AttachmentsDAO;
import com.joaovictoroliveira.api.exceptions.AttachmentNotFoundException;
import com.joaovictoroliveira.api.models.AttachmentModel;
import com.joaovictoroliveira.api.models.TaskModel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AttachmentsService {

    @Resource
    private AttachmentsDAO attachmentsDAO;
    @Resource
    private UsersService usersService;

    public AttachmentsService() {
    }

    public List<AttachmentModel> getAll(Integer taskId) {
        TaskModel taskModel = new TaskModel();
        taskModel.setId(taskId);
        return attachmentsDAO.findAllByTaskIdAndUser(taskModel, usersService.getCurrentUser());
    }

    public AttachmentModel getAttachment(Integer id) {
        AttachmentModel attachmentModel = attachmentsDAO.findByIdAndUser(id, usersService.getCurrentUser());
        if (attachmentModel == null) {
            throw new AttachmentNotFoundException();
        }
        return attachmentModel;
    }

    public AttachmentModel save(Integer taskId, MultipartFile file) throws IOException {
        TaskModel taskModel = new TaskModel();
        taskModel.setId(taskId);
        AttachmentModel attachmentModel = new AttachmentModel();
        attachmentModel.setTask(taskModel);
        attachmentModel.setFileName(file.getOriginalFilename().split("\\.")[0]);
        attachmentModel.setContentType(file.getContentType());
        attachmentModel.setUser(usersService.getCurrentUser());
        attachmentModel.setFileData(file.getBytes());
        return attachmentsDAO.save(attachmentModel);
    }

    public void delete(Integer id) {
        AttachmentModel attachmentModel = attachmentsDAO.findByIdAndUser(id, usersService.getCurrentUser());
        if (attachmentModel == null) {
            throw new AttachmentNotFoundException();
        }
        attachmentsDAO.delete(attachmentModel);
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public AttachmentsDAO getAttachmentsDAO() {
        return attachmentsDAO;
    }

    public void setAttachmentsDAO(AttachmentsDAO attachmentsDAO) {
        this.attachmentsDAO = attachmentsDAO;
    }
}
