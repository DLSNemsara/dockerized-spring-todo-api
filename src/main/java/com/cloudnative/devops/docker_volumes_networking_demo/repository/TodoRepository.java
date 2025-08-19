package com.cloudnative.devops.docker_volumes_networking_demo.repository;

import com.cloudnative.devops.docker_volumes_networking_demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
