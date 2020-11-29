package com.example.controllers;

import com.example.entities.ToDo;
import com.example.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {
    @Autowired
    ToDoRepository todoRepository;
    public ToDo addToDo(ToDo todo){
        return todoRepository.save(todo);
    }
}
