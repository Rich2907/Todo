package com.example.Todo.Service;

import com.example.Todo.Dto.TaskRequestDTO;
import com.example.Todo.Dto.TaskResponseDto;
import com.example.Todo.Entity.Task;
import com.example.Todo.Repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TaskService {
   @Autowired
   private TaskRepository taskRepository;

    public TaskResponseDto createTask(TaskRequestDTO request)
    {
        Task task =new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        Task savedTask = taskRepository.save(task);

        TaskResponseDto responseDTO = new TaskResponseDto();

        responseDTO.setId(savedTask.getId());

        responseDTO.setTitle(savedTask.getTitle());

        responseDTO.setDescription(
                savedTask.getDescription()
        );

        responseDTO.setStatus(
                savedTask.getStatus()
        );

        responseDTO.setPriority(
                savedTask.getPriority()
        );

        responseDTO.setDueDate(
                savedTask.getDueDate()
        );

        responseDTO.setCreatedAt(
                savedTask.getCreatedAt()
        );

        responseDTO.setUpdatedAt(
                savedTask.getUpdatedAt()
        );

        return responseDTO;
    }

    }

