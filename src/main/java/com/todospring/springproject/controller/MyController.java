package com.todospring.springproject.controller;

import org.springframework.ui.Model;

import com.todospring.springproject.entity.ToDoEntites;
import com.todospring.springproject.repositry.ToDoRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MyController {


    @Autowired
    private ToDoRepositry toDoRepositry;

    @GetMapping("/todo")
    public String ToDo(Model model){
        List<ToDoEntites> data = toDoRepositry.findAll();
        model.addAttribute("data", data);
        return "index";
    }

    @PostMapping("/todo/add")
    public String addTasks(@RequestParam("task") String task){
        ToDoEntites t=new ToDoEntites();
        t.setTasks(task);
        toDoRepositry.save(t);

        return "redirect:/todo";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTask(@PathVariable int id) {

        toDoRepositry.deleteById(id);
        // Redirect to the task list page after deletion
        return "redirect:/todo";
    }

    @PutMapping("/update/{id}")
    public String updateTask(@RequestParam("task") ToDoEntites task,@PathVariable int id){
        task.setId(id);
        toDoRepositry.save(task);
        return "redirect:/todo";

    }
}
