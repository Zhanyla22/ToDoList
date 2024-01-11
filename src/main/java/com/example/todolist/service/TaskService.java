package com.example.todolist.service;

import com.example.todolist.dto.request.AddTaskRequest;
import com.example.todolist.dto.request.UpdateTaskRequest;
import com.example.todolist.dto.response.TaskResponse;
import com.example.todolist.enums.Status;
import com.example.todolist.enums.TaskStatus;

import java.util.List;


public interface TaskService {

    TaskResponse addTask(AddTaskRequest addTaskRequest);

    TaskResponse getById(Long id);

    TaskResponse update(UpdateTaskRequest updateTaskRequest, Long id);

    TaskResponse deleteById(Long id);

    List<TaskResponse> getAll(Status status, TaskStatus taskStatus);

    List<TaskResponse> getAllOrderByCreatedDate();
}
