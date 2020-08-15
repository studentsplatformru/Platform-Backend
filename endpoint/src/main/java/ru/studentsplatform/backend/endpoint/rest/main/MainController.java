package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import static ru.studentsplatform.backend.system.helper.ControllerUtils.handleExceptions;


@Profiled
@RestController("/")
public class MainController {
	@GetMapping
	public ResponseEntity<String> getMain() throws Exception {
		return handleExceptions(() -> {
			if (true) {
				throw new BusinessException(ServiceExceptionReason.NOTE_NOT_FOUND);
			}
			return ResponseEntity.ok("Hello world");
		});
	}
}