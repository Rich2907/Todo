package com.example.Todo.Controller;

import com.example.Todo.Dto.TaskRequestDTO;
import com.example.Todo.Dto.TaskResponseDto;
import com.example.Todo.Dto.signupDto;
import com.example.Todo.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/tasks")
@RestController
@RequiredArgsConstructor
public class TaskContoller {

    @Autowired
    private  final TaskService taskService;
    @PostMapping("/createTask")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequestDTO request)
    {
         TaskResponseDto response=taskService.createTask(request);
        return ResponseEntity.ok(response);
    }
}
