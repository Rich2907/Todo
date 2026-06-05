package com.example.Todo.Dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedTaskResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;    private List<TaskResponseDto> content;

    private int currentPage;

    private int totalPages;

    private long totalElements;
}
