package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.notification.email.EMailSender;


//@Profiled
@RestController("/")
public class MainController {

	@Autowired
	private EMailSender sender;

	@GetMapping
	public ResponseEntity getMain() {
		return ResponseEntity.ok("Hello world");
	}

	@GetMapping("mail")
	public ResponseEntity<String> sendMail(
	) {
		sender.send("kar-dan2000@yandex.ru", "test subject", "test body, pal");
		return ResponseEntity.ok("Send");
	}
}