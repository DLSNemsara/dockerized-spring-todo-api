package com.cloudnative.devops.docker_volumes_networking_demo.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("Todo not found: " + id);
    }
}
