package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;


@Profiled
@RestController("/")
public class MainController {

	@Autowired
	private NotifyService notifyService;

	@Autowired
	private UserRepository repository;

	@GetMapping
	public ResponseEntity getMain() {
		return ResponseEntity.ok("Hello world");
	}

	@GetMapping("mail")
	public ResponseEntity getMail() {

		notifyService.sendNotification(
				repository.findById(2L).get(),
				MessageType.EMAIL_CONFIRMATION,
				"123");
		return ResponseEntity.ok("mail send");
	}

	@GetMapping("custom")
	public ResponseEntity getMai() {

		notifyService.sendNotification(
				repository.findById(2L).get(),
				MessageType.CUSTOM,
				"123");
		return ResponseEntity.ok("custom send");
	}

}