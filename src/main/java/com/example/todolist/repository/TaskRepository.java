package com.example.todolist.repository;

import com.example.todolist.entity.Task;
import com.example.todolist.enums.Status;
import com.example.todolist.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR t.status = :status)" +
            "AND (:taskStatus IS NULL OR t.taskStatus = :taskStatus)" +
            "order by t.createdDate DESC ")
    List<Task> findAllByStatusAndTaskStatus(Status status, TaskStatus taskStatus);

    List<Task> findAllByOrderByCreatedDateDesc();
}
