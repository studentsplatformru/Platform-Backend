package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/")
public class MainController {

	@GetMapping
	public ResponseEntity getMain() {
		return ResponseEntity.ok("Hello world");
	}


}