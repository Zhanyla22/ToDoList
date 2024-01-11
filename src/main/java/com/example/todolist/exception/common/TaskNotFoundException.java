package com.example.todolist.exception.common;

import com.example.todolist.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Кастомное исключение выбрасывется при попытке получения инфо о несуществующей задаче
 */
public class TaskNotFoundException extends BaseException {
    public TaskNotFoundException(Long id, HttpStatus httpStatus) {
        super("Task not found by id - " + id, httpStatus);
    }
}
