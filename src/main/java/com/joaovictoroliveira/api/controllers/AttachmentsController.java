package com.joaovictoroliveira.api.controllers;

import com.joaovictoroliveira.api.models.AttachmentModel;
import com.joaovictoroliveira.api.services.AttachmentsService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tasks/attachments")
public class AttachmentsController {

    @Resource
    private AttachmentsService attachmentsService;

    @GetMapping
    public ResponseEntity getAllAttachments(@RequestHeader() Integer taskId) {
        return ResponseEntity.ok(attachmentsService.getAll(taskId));
    }

    @GetMapping("/{id}/{fileName}")
    public ResponseEntity getAttachment(@PathVariable() Integer id, @RequestParam(value = "w", required = false) String download) {
        AttachmentModel attachment = attachmentsService.getAttachment(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header("Content-Disposition", download != null ? download.equals("download") ? "attachment" : "" : "")
                .contentLength(attachment.getFileData().length)
                .body(attachment.getFileData());
    }

    @PostMapping
    public ResponseEntity saveAttachment(@RequestHeader() Integer taskId, @RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.ok(attachmentsService.save(taskId, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAttachment(@PathVariable() Integer id) {
        attachmentsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
