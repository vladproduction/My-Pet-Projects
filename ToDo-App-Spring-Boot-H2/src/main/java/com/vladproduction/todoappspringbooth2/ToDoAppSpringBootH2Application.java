package com.vladproduction.todoappspringbooth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoAppSpringBootH2Application {

	public static void main(String[] args) {
		SpringApplication.run(ToDoAppSpringBootH2Application.class, args);
	}

}

/**
 * By default, the H2 console is not enabled in Spring.
 * To enable it, we need to add the following property to application.properties:
 * spring.h2.console.enabled=true
 *
 * Then, after starting the application, we can navigate to :
 * 		http://localhost:8080/h2-console
 * which will present us with a login page.
 * On the login page, we’ll supply the same credentials that we used in the application.properties:*/
