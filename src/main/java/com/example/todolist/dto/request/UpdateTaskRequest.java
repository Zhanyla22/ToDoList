package com.example.todolist.dto.request;

import com.example.todolist.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTaskRequest {

    String description;

    @JsonProperty("task_status")
    TaskStatus status;
}
