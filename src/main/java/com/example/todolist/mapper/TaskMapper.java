package com.example.todolist.mapper;

import com.example.todolist.dto.request.AddTaskRequest;
import com.example.todolist.dto.response.TaskResponse;
import com.example.todolist.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    /**
     * При добавлении задачи, у задачи автоматически status - ACTIVE, taskStatus - NOT_EXECUTED(не выполнено)
     * @param addTaskRequest
     * @return
     */
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "taskStatus", constant = "NOT_EXECUTED")
    Task toEntity(AddTaskRequest addTaskRequest);

    TaskResponse toModel(Task task);

    List<TaskResponse> toModelList(List<Task> tasks);
}
