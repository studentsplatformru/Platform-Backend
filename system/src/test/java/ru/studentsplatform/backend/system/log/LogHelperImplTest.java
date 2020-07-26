package ru.studentsplatform.backend.system.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.system.log.impl.LogHelperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс проверки реализаций методов работы с log.
 */
@ExtendWith(MockitoExtension.class)
class LogHelperImplTest {
	private static final String SEPARATOR = System.getProperty("line.separator");

	private LogHelperImpl logHelper;

	@BeforeEach
	void before() {
		var fileName = "src/test/resources/TestFile.txt";
		logHelper = new LogHelperImpl(fileName);
	}

	@Test
	void getLogsAllFileTest() {
		var result = logHelper.getLogs(null);
		var stringBuilder = new StringBuilder();
		for (var i = 1; i < 11; i++) {
			stringBuilder.append(i);
			stringBuilder.append(SEPARATOR);
		}
		var expected = stringBuilder.toString();
		assertEquals(expected, result);
	}

	@Test
	void getLastLogLinesTest() {
		var result = logHelper.getLogs(5L);
		var stringBuilder = new StringBuilder();
		for (var i = 6; i < 11; i++) {
			stringBuilder.append(i);
			stringBuilder.append(SEPARATOR);
		}
		var expected = stringBuilder.toString();
		assertEquals(expected, result);
	}
}