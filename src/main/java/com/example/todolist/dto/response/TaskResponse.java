package com.example.todolist.dto.response;

import com.example.todolist.enums.Status;
import com.example.todolist.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {

    Long id;
    String description;
    TaskStatus taskStatus;
    Status status;
}
