package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.service.EMailSender;


@RestController
@RequestMapping("/")
public class MainController {

	@Autowired
	private EMailSender eMailSender;

	@GetMapping
	public ResponseEntity getMain() {
		return ResponseEntity.ok("Hello world");
	}

	@GetMapping("mail")
	public String mail()
	{
		eMailSender.send("danil-ors@mail.ru", "Kut IAS-17", "Hello i CAnt");
		return "send";
	}

}
