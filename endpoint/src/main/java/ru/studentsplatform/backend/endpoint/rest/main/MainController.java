package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
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
				MessageType.MARK_NOTIFICATION,
				"Математика", "4");
		return ResponseEntity.ok("Send");
	}
}