package ru.studentsplatform.backend.system.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.system.log.LogHelper;

/**
 * Контроллер позволяющий вызывать утилитные методы для просмотра или скачивания логов.
 *
 * @author Krylov Sergey (25.07.2020)
 */
@RestController
@RequestMapping(AbstractController.BASE_URL + "/log")
public class LogController extends AbstractController {

	private final LogHelper logHelper;

	/**
	 * Конструктор.
	 *
	 * @param logHelper Класс реализующий методы работы с log
	 */
	public LogController(LogHelper logHelper) {
		this.logHelper = logHelper;
	}


	@GetMapping("")
	public byte[] logFile(@RequestParam(value = "lines", required = false) Long lines) {
		return logHelper.getLogs(lines);
	}
}
