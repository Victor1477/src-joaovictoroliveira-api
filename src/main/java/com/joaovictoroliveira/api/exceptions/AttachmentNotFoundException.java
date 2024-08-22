package com.joaovictoroliveira.api.exceptions;

public class AttachmentNotFoundException extends RuntimeException {
    public AttachmentNotFoundException() {
        super("Attachment not found");
    }
}
