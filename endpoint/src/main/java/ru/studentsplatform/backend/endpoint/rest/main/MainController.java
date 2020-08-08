package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@Profiled
@RestController("/")
public class MainController {

	@GetMapping
	public ResponseEntity getMain() {
		return ResponseEntity.ok("Hello world");
	}
}