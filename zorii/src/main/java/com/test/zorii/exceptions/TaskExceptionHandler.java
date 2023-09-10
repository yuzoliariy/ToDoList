package com.test.zorii.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(value = PropertyValueException.class)
    public ResponseEntity<String> handleNullValue() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some of the required field are missing.");
    }

    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFound(TaskNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
