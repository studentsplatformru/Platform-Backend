package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.service.email.EMailSender;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;


@Profiled
@RestController("/")
public class MainController {
	private final EMailSender eMailSender;

	public MainController(EMailSender eMailSender) {
		this.eMailSender = eMailSender;
	}

	@GetMapping
	public ResponseEntity getMain() {
		eMailSender.send("krylov.sergey.1999@yandex.ru", "Test", "Test body");
		eMailSender.send("krylov.sergey.1999@yandex.ru", "Test", "Test body");
		return ResponseEntity.ok("Hello world");
	}
}