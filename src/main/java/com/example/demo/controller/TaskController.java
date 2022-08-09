package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.TaskEntity;
import com.example.demo.repository.TaskRepository;

@Controller
@RequestMapping(path = "/")
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
        task.setStatus(0);

        taskRepository.save(task);
        
        return "redirect:/";
    }

    @RequestMapping("/")
    public String list_task(Model model) {

        model.addAttribute("taskList", taskRepository.findAll());

        return "list_task";
        
    }

    @GetMapping("/edit-task/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        TaskEntity task = taskRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        
        model.addAttribute("description", task.getDescription());
        model.addAttribute("id", id);
        model.addAttribute("category", task.getCategory());

        return "edit_task";
    }

    @PostMapping("/update-task/{id}")
    public String updateTask(@PathVariable("id") long id, @RequestParam String description, @RequestParam String category, Model model) {

        TaskEntity task = taskRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));

        task.setDescription(description);
        task.setCategory(category);
            
        taskRepository.save(task);

        return "redirect:/";
    }

    @GetMapping("/check-task/{id}")
    public String checkTask(@PathVariable("id") long id, Model model) {

        TaskEntity task = taskRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));

        task.setStatus(1);
            
        taskRepository.save(task);

        return "redirect:/";
    }

    @GetMapping("/uncheck-task/{id}")
    public String uncheckTask(@PathVariable("id") long id, Model model) {

        TaskEntity task = taskRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));

        task.setStatus(0);
            
        taskRepository.save(task);

        return "redirect:/";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable("id") long id, Model model) {

        TaskEntity task = taskRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
            
        taskRepository.delete(task);

        return "redirect:/";
    }

}
