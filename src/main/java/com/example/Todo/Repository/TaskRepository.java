package com.example.Todo.Repository;

import com.example.Todo.Entity.Task;
import com.example.Todo.Enums.Priority;
import com.example.Todo.Enums.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findByStatus(
            Status status,
            Pageable pageable
    );

    Page<Task> findByPriority(
            Priority priority,
            Pageable pageable
    );

    Page<Task> findByTitleContainingIgnoreCase(
            String Title,
            Pageable pageable
    );
}
