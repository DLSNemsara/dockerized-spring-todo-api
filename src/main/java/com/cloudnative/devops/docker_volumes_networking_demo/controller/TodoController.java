package com.cloudnative.devops.docker_volumes_networking_demo.controller;

import com.cloudnative.devops.docker_volumes_networking_demo.model.Todo;
import com.cloudnative.devops.docker_volumes_networking_demo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    private final TodoService service;
    public TodoController(TodoService service) {
        this.service =  service;
    }

    @GetMapping
    public List<Todo> getAll() {
        logger.info("Fetching all todos");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getOne(@PathVariable Long id) {
        logger.info("Fetching todo with id {}", id);
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        logger.info("Creating new todo: {}", todo.getTitle());
        Todo saved = service.create(todo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                        .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo updated) {
        logger.info("Updating todo id {} with title {}", id, updated.getTitle());
        return ResponseEntity.ok(service.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting todo with id {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
