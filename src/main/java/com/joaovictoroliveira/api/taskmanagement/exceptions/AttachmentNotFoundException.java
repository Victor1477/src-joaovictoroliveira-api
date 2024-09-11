package com.joaovictoroliveira.api.taskmanagement.exceptions;

public class AttachmentNotFoundException extends RuntimeException {
    public AttachmentNotFoundException() {
        super("Attachment not found");
    }
}
