package com.example.Todo.Service;
import com.example.Todo.Enums.Priority;
import com.example.Todo.Enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    private static final Logger logger =
            LoggerFactory.getLogger(TaskService.class);
    @Autowired
    private TaskRepository taskRepository;

    @CacheEvict(value = "tasks", allEntries = true)
    public TaskResponseDto createTask(TaskRequestDTO request) {
        logger.info("Creating new task: {}", request.getTitle());
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
    @Cacheable("Tasks")
    public PaginatedTaskResponseDto getAllTasks(int page, int size, Status status, Priority priority, String search) {

        Pageable pageable= PageRequest.of(page, size);
        Page<Task> taskPage;
        System.out.println("Cache Miss");
        logger.info("Fetching all tasks");
        if( status !=null)
        {
            System.out.println(status);
            taskPage=taskRepository.findByStatus(status,pageable);
        }
        else if (priority != null)
        {
         taskPage=taskRepository.findByPriority(priority,pageable);
        }
        else if( search!=null && !search.isEmpty())
        {
            taskPage=taskRepository.findByTitleContainingIgnoreCase(search,pageable);
        }
        else {
            taskPage = taskRepository.findAll(pageable);
        }
        System.out.println(status+" "+priority+" "+search);
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
        logger.info("Total tasks fetched: {}", response.size());
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

