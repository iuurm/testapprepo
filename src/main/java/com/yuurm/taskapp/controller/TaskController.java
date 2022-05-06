package com.yuurm.taskapp.controller;

import com.yuurm.taskapp.model.Task;
import com.yuurm.taskapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("tasks/{id}")
    public Task getById(@PathVariable Long id) {
        return taskRepository.findById(id).get();
    }

    @PutMapping("tasks/{id}")
    public Task update(@PathVariable Long id,
                       @RequestBody Task task){
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

    @PatchMapping("tasks/{id}")
    public void patch(@PathVariable
Long id,@RequestBody Task task){
        //task.setId(id);
        //taskRepository.save(task);
        if(task.isDone()){
            taskRepository.markAsDone(id);
        }
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id){
        taskRepository.markAsDone(id);
    }

}
