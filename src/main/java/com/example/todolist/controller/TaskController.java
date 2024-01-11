package com.example.todolist.controller;

import com.example.todolist.dto.request.AddTaskRequest;
import com.example.todolist.dto.request.UpdateTaskRequest;
import com.example.todolist.dto.response.TaskResponse;
import com.example.todolist.enums.Status;
import com.example.todolist.enums.TaskStatus;
import com.example.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @Operation(summary = "Add new Task")
    @PostMapping("/add")
    public ResponseEntity<TaskResponse> add(@RequestBody AddTaskRequest addTaskRequest) {
        return ResponseEntity.ok().body(taskService.addTask(addTaskRequest));
    }

    @Operation(summary = "Get Task by id")
    @GetMapping("/get/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.getById(id));
    }

    @Operation(summary = "Update Task by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponse> update(@RequestBody UpdateTaskRequest updateTaskRequest, @PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.update(updateTaskRequest, id));
    }

    @Operation(summary = "Delete Task by id")
    @PutMapping("/delete/{id}")
    public ResponseEntity<TaskResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.deleteById(id));
    }

    @Operation(summary = "Get All Task by filter, 1) empty parameters execute gets all tasks without filter. 2) Can be filtered only by one parameter ")
    @GetMapping("/get-all")
    public ResponseEntity<List<TaskResponse>> getAll(@RequestParam(required = false) Status status,
                                                     @RequestParam(required = false) TaskStatus taskStatus) {
        if (taskStatus != null || status != null) {
            return ResponseEntity.ok().body(taskService.getAll(status, taskStatus));
        } else
            return ResponseEntity.ok().body(taskService.getAllOrderByCreatedDate());
    }
}
