package com.todospring.springproject.repositry;

import com.todospring.springproject.entity.ToDoEntites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepositry extends JpaRepository<ToDoEntites, Integer> {
    
}
