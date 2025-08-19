package com.cloudnative.devops.docker_volumes_networking_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TodoAppDockerDevopsApplication {

	private static final Logger logger = LoggerFactory.getLogger(TodoAppDockerDevopsApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(TodoAppDockerDevopsApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logStartup() {
		logger.info("Application has started successfully and is ready to handle requests!");
	}
}
