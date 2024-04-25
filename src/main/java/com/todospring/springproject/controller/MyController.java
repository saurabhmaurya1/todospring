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


    @GetMapping("/update/{id}")
    public String editTask(@PathVariable("id") int id,Model model){
        Optional<ToDoEntites> t = toDoRepositry.findById(id);
        model.addAttribute("data",t.get());
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@RequestParam("task") String task,@PathVariable int id){
        ToDoEntites t=new ToDoEntites();
        t.setTasks(task);
        t.setId(id);
        toDoRepositry.save(t);
        return "redirect:/todo";

    }
}
