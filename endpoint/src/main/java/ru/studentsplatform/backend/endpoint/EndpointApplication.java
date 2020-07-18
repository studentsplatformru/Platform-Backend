package ru.studentsplatform.backend.endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.studentsplatform.backend.*"})
@EntityScan(basePackages = {"ru.studentsplatform.backend.*"})
public class EndpointApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(EndpointApplication.class, args);
    }
}
