package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.system.exception.core.Fault;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.alarm.AlarmController;

import java.util.Collections;

import static ru.studentsplatform.backend.system.helper.ControllerUtils.handleExceptions;

@Profiled
@RestController("/")
public class MainController {

	@Autowired
	private AlarmController alarmController;

	@Autowired
	private NotifyService notifyService;

	@GetMapping
	public ResponseEntity<String> getMain() throws Fault {
		return handleExceptions(() -> ResponseEntity.ok("Hello world"));
	}

	@GetMapping("mail")
	public ResponseEntity<String> getM() throws Fault {

		alarmController.activateEventNotifyAlarm();

		return handleExceptions(() -> ResponseEntity.ok("message send?"));
	}

	@GetMapping("telegram")
	public ResponseEntity<String> getT() throws Fault {

		TelegramMessageDTO dto = new TelegramMessageDTO();
		dto.setText("Test");
		dto.setId(479613821L);

		notifyService.sendListMessagesTelegram(Collections.singletonList(dto));

		return handleExceptions(() -> ResponseEntity.ok("message send?"));
	}

}