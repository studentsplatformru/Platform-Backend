package ru.studentsplatform.backend.endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.studentsplatform.backend.*"})
@EntityScan(basePackages = {"ru.studentsplatform.backend.*"})
@EnableJpaRepositories(basePackages = "ru.studentsplatform.backend.domain.repository")
public class EndpointApplication {

	{
		ApiContextInitializer.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(EndpointApplication.class, args);
	}
}