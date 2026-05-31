package com.example.Todo.Service;

import com.example.Todo.Dto.TaskRequestDTO;
import com.example.Todo.Dto.TaskResponseDto;
import com.example.Todo.Entity.Task;
import com.example.Todo.Exception.ResourceNotFoundException;
import com.example.Todo.Repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskResponseDto createTask(TaskRequestDTO request) {
        Task task = new Task();
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

    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDto> response = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponseDto dto = new TaskResponseDto();
            dto.setId((task.getId()));
            dto.setTitle(task.getTitle());

            dto.setDescription(
                    task.getDescription()
            );

            dto.setStatus(
                    task.getStatus()
            );

            dto.setPriority(
                    task.getPriority()
            );

            dto.setDueDate(
                    task.getDueDate()
            );

            dto.setCreatedAt(
                    task.getCreatedAt()
            );

            dto.setUpdatedAt(
                    task.getUpdatedAt()
            );

            response.add(dto);
        }
        return response;
    }
    public TaskResponseDto gettaskByid( Long id )
    {
       Task task= taskRepository.findById(id)
                       .orElseThrow(()-> new ResourceNotFoundException("Task not found"));
        TaskResponseDto dto = new TaskResponseDto();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());

        dto.setDescription(
                task.getDescription()
        );

        dto.setStatus(
                task.getStatus()
        );

        dto.setPriority(
                task.getPriority()
        );

        dto.setDueDate(
                task.getDueDate()
        );

        dto.setCreatedAt(
                task.getCreatedAt()
        );

        dto.setUpdatedAt(
                task.getUpdatedAt()
        );
        return dto;
    }
    public TaskResponseDto updateTaskById( Long id,TaskRequestDTO request ) {
        Task task= taskRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Task not found"));
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
    public void deleteTaskByid(Long id)
    {
        taskRepository.deleteById(id);
    }

}

