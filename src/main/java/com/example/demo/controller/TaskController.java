package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.TaskEntity;
import com.example.demo.repository.TaskRepository;

@Controller
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable <TaskEntity> getTasks() {
        return taskRepository.findAll();
    }

    @RequestMapping("/new-task")
    public String new_task() {

        return "new_task";
        
    }

    @PostMapping(path = "/add")
    public String addNewTask(@RequestParam String description, @RequestParam String category) {

        TaskEntity task = new TaskEntity();

        task.setDescription(description);
        task.setCategory(category);
        task.setStatus(null);

        taskRepository.save(task);
        
        return "redirect:/task/list-task/";
    }

    @RequestMapping("/list-task")
    public String list_task(Model model) {

        model.addAttribute("taskList", taskRepository.findAll());

        return "list_task";
        
    }

}
