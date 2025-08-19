package com.cloudnative.devops.docker_volumes_networking_demo.service;

import com.cloudnative.devops.docker_volumes_networking_demo.exception.TodoNotFoundException;
import com.cloudnative.devops.docker_volumes_networking_demo.model.Todo;
import com.cloudnative.devops.docker_volumes_networking_demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

    private  final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> findAll() {
        logger.info("Service: Fetching all todos");
        return repo.findAll();
    }

    public Todo findById(Long id) {
        logger.info("Service: Fetching todo by id {}", id);
        return repo.findById(id).orElseThrow(() -> {
            logger.warn("Todo with id {} not found", id);
            return new TodoNotFoundException(id);
        });
    }

    public Todo create(Todo todo) {
        logger.info("Service: Creating todo {}", todo.getTitle());
        todo.setId(null);
        return repo.save(todo);
    }

    public Todo update(Long id, Todo updated) {
        logger.info("Service: Updating todo {}", id);
        Todo existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setDone((updated.isDone()));
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            logger.warn("Service: Attempted to delete non-existing todo {}", id);
            throw new TodoNotFoundException(id);
        }
        logger.info("Service: Deleting todo {}", id);
        repo.deleteById(id);
    }
}
