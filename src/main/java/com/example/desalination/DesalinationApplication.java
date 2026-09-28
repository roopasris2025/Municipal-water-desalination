package com.example.desalination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the whole project.
 *
 * @SpringBootApplication = combination of:
 *   @Configuration      -> this class can define beans
 *   @EnableAutoConfiguration -> Spring Boot auto-configures Web MVC, JPA, Thymeleaf etc.
 *   @ComponentScan      -> scans com.example.desalination and all sub-packages
 *                          (controller, service, repository, entity) and registers them as beans.
 *
 * Running this class starts an embedded Tomcat server on port 8080.
 */
@SpringBootApplication
public class DesalinationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesalinationApplication.class, args);
    }
}
