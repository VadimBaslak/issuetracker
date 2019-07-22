package com.vbaslak.issuetracker.controller;

import com.vbaslak.issuetracker.domain.Task;
import com.vbaslak.issuetracker.domain.User;
import com.vbaslak.issuetracker.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String taskPage(Map<String, Object> model) {
        Iterable<Task> tasks = taskRepository.findAll();
        model.put("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/newTask")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String taskName,
            @RequestParam String description,
            Map<String, Object> model
    ){
        Task task = new Task(taskName, user, description);
        taskRepository.save(task);
        return taskPage(model);
    }

    @PostMapping("/filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model
    ){
        Iterable<Task> tasks;

        if(filter != null && !filter.isEmpty()) {
            tasks = taskRepository.findByTaskName(filter);
        } else {
            tasks = taskRepository.findAll();
        }
        model.put("tasks", tasks);
        return "tasks";
    }
}