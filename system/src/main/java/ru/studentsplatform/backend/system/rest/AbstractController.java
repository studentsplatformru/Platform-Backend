package ru.studentsplatform.backend.system.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Krylov Sergey (25.07.2020)
 */
@RestController
@RequestMapping(AbstractController.BASE_URL)
public abstract class AbstractController {
	static final String BASE_URL = "/api/system";
}
