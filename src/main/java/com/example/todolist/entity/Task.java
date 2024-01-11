package com.example.todolist.entity;


import com.example.todolist.enums.Status;
import com.example.todolist.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description")
    String description;

    @Enumerated(EnumType.STRING)
    TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Column(name = "updated_date")
    LocalDateTime updatedDate;

    /**
     * при добавлении записи
     * берет дату(LocalDateTime) на данный момент
     * и сохраняет на поле created_date
     */
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    /**
     * при обновлении  записи
     * берет дату(LocalDateTime) на данный момент
     * и сохраняет на поле updated_date
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
