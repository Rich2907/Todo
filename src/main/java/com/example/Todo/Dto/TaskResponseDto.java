package com.example.Todo.Dto;

import com.example.Todo.Enums.priority;
import com.example.Todo.Enums.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private Long id;

    private String title;

    private String description;

    private status status;

    private priority priority;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}