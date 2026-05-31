package com.example.Todo.Entity;

import com.example.Todo.Enums.priority;
import com.example.Todo.Enums.status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank(message = "Title is required")
    private String Title;
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private status Status;

    @NotNull(message = "Priority is required")
    @Enumerated(EnumType.STRING)
    private priority priority;


    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

    @PrePersist
    public void prePersist() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {

        updatedAt = LocalDateTime.now();
    }
}

