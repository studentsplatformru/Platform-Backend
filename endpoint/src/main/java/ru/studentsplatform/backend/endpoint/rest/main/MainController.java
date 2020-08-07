package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.notification.MessageType;
import ru.studentsplatform.backend.notification.NotifyController;


//@Profiled
@RestController("/")
public class MainController {

	@Autowired
	private NotifyController controller;

	@GetMapping
	public ResponseEntity getMain() {
		return ResponseEntity.ok("Hello world");
	}

	@GetMapping("mail")
	public ResponseEntity<String> sendMail(
	) {
		controller.sendNotification(
				"kar-dan2000@yandex.ru",
				MessageType.EMAIL_CONFIRMATION,
				"https://www.google.com/");
		return ResponseEntity.ok("Send");
	}
}