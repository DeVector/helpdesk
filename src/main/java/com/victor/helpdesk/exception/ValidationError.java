package com.victor.helpdesk.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class ValidationError extends ExceptionResponse{

    private List<FieldMessage> list = new ArrayList<>();

    public ValidationError(int status, String message, String error, String path){
        super(status, message, error, path);
    }

    public void addErrors(String fieldName, String fieldMessage){
        this.list.add(new FieldMessage(fieldName, fieldMessage));
    }
}
