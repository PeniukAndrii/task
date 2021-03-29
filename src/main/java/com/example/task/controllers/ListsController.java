package com.example.task.controllers;

import com.example.task.dao.ListsDAO;
import com.example.task.dao.TodoDAO;
import com.example.task.models.Lists;
import com.example.task.models.Todo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import javax.persistence.JoinColumn;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://localhost:4200/todo",
        "http://localhost:4200/todoget",
        "http://localhost:4200/todosave",
        "http://localhost:4200/todochange",
        "http://localhost:4200/todolist",
        "http://localhost:4200/tododelte",})
public class ListsController {
    private ListsDAO listsDAO;
    private TodoDAO todoDao;

    @PostMapping("/save")
    public void save(@RequestBody Lists lists) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        lists.setCreatedAt(formatter.format(date));
        listsDAO.save(lists);
    }

    @PostMapping("/edit")
    public void edit(@RequestBody Lists lists) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        lists.setUpdatedAt(formatter.format(date));
        Lists byId = listsDAO.getOne(lists.getId());
        List<Todo> todos = byId.getTodo();
        byId.setTitle(lists.getTitle());
        byId.setTodo(todos);
        listsDAO.save(byId);
    }

    @PostMapping("/todochange")
    public void todoChange(@PathVariable int id, @RequestBody Todo todo) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        Lists byId = listsDAO.getOne(id);
        byId.setUpdatedAt(formatter.format(date));
        List<Todo> todo1 = byId.getTodo();
        todo1.add(todo);
        byId.setTodo(todo1);
        listsDAO.save(byId);
    }

    @PostMapping("/todosave/{id}")
    public void saveTodo(@PathVariable int id,
                         @RequestBody Todo todo) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis() + 9999999);
        todo.setFinalDate(formatter.format(date));

        Lists byId = listsDAO.getOne(id);
        List<Todo> todos = byId.getTodo();
        if (todo.getTitle() != null || todo.getBody() != null) {
            todos.add(todo);
        }
        byId.setTodo(todos);
        listsDAO.save(byId);
    }

    @GetMapping("/todolist/{id}")
    public List<Todo> getTodos(@PathVariable int id) {
        Lists byId = listsDAO.getOne(id);
        return byId.getTodo();
    }

    @GetMapping("/get")
    public List<Lists> get() {
        return listsDAO.findAll();
    }

    @GetMapping("/find")
    public Stream<Lists> find(@RequestParam String text) {
        return listsDAO.findAll().stream().filter(list -> list.getTitle().contains(text));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        listsDAO.deleteById(id);
    }

}
