package com.example.Todo.Dto;

import com.example.Todo.Enums.Priority;
import com.example.Todo.Enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}