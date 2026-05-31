package com.example.Todo.Controller;

import com.example.Todo.Dto.PaginatedTaskResponseDto;
import com.example.Todo.Dto.TaskRequestDTO;
import com.example.Todo.Dto.TaskResponseDto;
import com.example.Todo.Dto.signupDto;
import com.example.Todo.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tasks")
@RestController
@RequiredArgsConstructor
public class TaskContoller {

    @Autowired
    private  final TaskService taskService;
    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequestDTO request)
    {
         TaskResponseDto response=taskService.createTask(request);
        return ResponseEntity.ok(response);
    }
   @GetMapping
   public ResponseEntity<PaginatedTaskResponseDto> getAllTasks( @RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "5") int size)
   {
       return ResponseEntity.ok(taskService.getAllTasks(page,size));
   }

   @GetMapping("{id}")
    public ResponseEntity<?> getByid(@PathVariable("id") Long Taskid)
   {
       return ResponseEntity.ok(taskService.gettaskByid(Taskid));
   }
   @PutMapping("{id}")

    public ResponseEntity<?>  updateTaskByid(@PathVariable("id") Long taskid,@RequestBody TaskRequestDTO request)
   {
       return ResponseEntity.ok(taskService.updateTaskById(taskid,request));
   }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long Taskid)
    {
        taskService.deleteTaskByid(Taskid);
        return ResponseEntity.ok("Deleted successfully");
    }
}
