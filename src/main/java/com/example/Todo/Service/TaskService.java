package com.example.Todo.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import com.example.Todo.Dto.PaginatedTaskResponseDto;
import com.example.Todo.Dto.TaskRequestDTO;
import com.example.Todo.Dto.TaskResponseDto;
import com.example.Todo.Entity.Task;
import com.example.Todo.Exception.ResourceNotFoundException;
import com.example.Todo.Repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public PaginatedTaskResponseDto getAllTasks(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        Page<Task> taskPage=taskRepository.findAll(pageable);
        List<TaskResponseDto> response = new ArrayList<>();
        for (Task task : taskPage.getContent()) {
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
        PaginatedTaskResponseDto Pageresponse =new PaginatedTaskResponseDto();
        Pageresponse.setContent(response);
//        Pageresponse.setCurrentPage(taskPage.getNumber());
        Pageresponse.setCurrentPage(taskPage.getNumber());

        Pageresponse.setTotalPages(taskPage.getTotalPages());

        Pageresponse.setTotalElements(taskPage.getTotalElements());

        return Pageresponse;


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

