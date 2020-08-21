package ru.studentsplatform.backend.endpoint.rest.main;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.system.exception.core.Fault;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import static ru.studentsplatform.backend.system.helper.ControllerUtils.handleExceptions;


@Profiled
@RestController("/")
public class MainController {

	@GetMapping
	public ResponseEntity<String> getMain() throws Fault {
		return handleExceptions(() -> ResponseEntity.ok("Hello world"));
	}

}