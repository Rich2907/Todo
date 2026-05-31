package com.example.Todo.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedTaskResponseDto {
    private List<TaskResponseDto> content;

    private int currentPage;

    private int totalPages;

    private long totalElements;
}
