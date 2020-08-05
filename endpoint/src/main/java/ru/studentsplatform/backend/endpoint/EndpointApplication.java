package ru.studentsplatform.backend.endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.QuerydslWebConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.studentsplatform.backend.*"})
@EntityScan(basePackages = {"ru.studentsplatform.backend.*"})
@EnableJpaRepositories(basePackages = "ru.studentsplatform.backend.domain.repository")
@EnableSpringDataWebSupport
@Import(QuerydslWebConfiguration.class)
@EnableWebMvc
public class EndpointApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(EndpointApplication.class, args);
    }
}