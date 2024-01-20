package com.example.project_for_university.exception;

import lombok.Data;

@Data
public class CustomException extends Exception {
    private String type;
    public CustomException(String message, String type) {
        super(message);
        this.type = type;
    }
}
