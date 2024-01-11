package com.example.todolist.service.impl;

import com.example.todolist.dto.request.AddTaskRequest;
import com.example.todolist.dto.request.UpdateTaskRequest;
import com.example.todolist.dto.response.TaskResponse;
import com.example.todolist.entity.Task;
import com.example.todolist.enums.Status;
import com.example.todolist.enums.TaskStatus;
import com.example.todolist.exception.common.TaskNotFoundException;
import com.example.todolist.mapper.TaskMapper;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;

    /**
     * Добавление новой задачи
     * @param addTaskRequest - description - описание
     * @return TaskResponse
     */
    @Override
    public TaskResponse addTask(AddTaskRequest addTaskRequest) {
        Task task = taskMapper.toEntity(addTaskRequest);
        return taskMapper.toModel(taskRepository.save(task));
    }

    /**
     * Получение задачи по id(и удаленную задачу и активную можно)
     * @param id - id задачи
     * @return TaskResponse
     */
    @Override
    public TaskResponse getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(id, HttpStatus.NOT_FOUND)
        );

        return taskMapper.toModel(task);
    }

    /**
     *
     * @param updateTaskRequest - taskStatus - статус задачи (DONE-выполнено, NOT_EXECUTED -не выполнено); description - описание
     *                          status - меняется на UPDATED
     * @param id
     * @return TaskResponse
     */
    @Override
    public TaskResponse update(UpdateTaskRequest updateTaskRequest, Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(id, HttpStatus.NOT_FOUND)
        );

        task.setDescription(updateTaskRequest.getDescription());
        task.setTaskStatus(updateTaskRequest.getStatus());
        task.setStatus(Status.UPDATED);

        return taskMapper.toModel(taskRepository.save(task));
    }

    /**
     * Удаление задачи по id
     * status - меняется на DELETED
     * @param id
     * @return TaskResponse
     */
    @Override
    public TaskResponse deleteById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException(id, HttpStatus.NOT_FOUND)
        );
        task.setStatus(Status.DELETED);
        return taskMapper.toModel(taskRepository.save(task));
    }

    /**
     * Получение всей задачи(имеется фильтрация по status(ACTIVE, DELETED, UPDATED), taskStatus(DONE, NOT_EXECUTED))
     * @param status
     * @param taskStatus
     * @return
     */
    @Override
    public List<TaskResponse> getAll(Status status, TaskStatus taskStatus) {
        List<Task> tasks = taskRepository.findAllByStatusAndTaskStatus(status, taskStatus);
        return taskMapper.toModelList(tasks);
    }

    /**
     * Получение всей задачи с фильтрацией по дате создания(и ACTIVE, DELETED, UPDATED),
     * @return
     */
    @Override
    public List<TaskResponse> getAllOrderByCreatedDate() {
        List<Task> tasks = taskRepository.findAllByOrderByCreatedDateDesc();
        return taskMapper.toModelList(tasks);
    }
}
