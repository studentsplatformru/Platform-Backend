package ru.studentsplatform.backend.endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.ApiContextInitializer;
import ru.studentsplatform.backend.endpoint.config.FeignConfig;

@EnableFeignClients(basePackages = "ru.studentsplatform.backend.service", defaultConfiguration = FeignConfig.class)
@SpringBootApplication
@ComponentScan(basePackages = {"ru.studentsplatform.backend.*"})
@EntityScan(basePackages = {"ru.studentsplatform.backend.*"})
@EnableJpaRepositories(basePackages = "ru.studentsplatform.backend.domain.repository")
public class EndpointApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(EndpointApplication.class, args);
    }
}