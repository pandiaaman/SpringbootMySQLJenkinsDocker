package com.springSQLJenkinsDocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringSqlJenkinsDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSqlJenkinsDockerApplication.class, args);
		log.info("spring boot application running...");
	}

}

/*
	Filters
	AOP
	ExceptionHandling Advice's
	cache
	unit testing
*/