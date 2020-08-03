package ru.studentsplatform.backend.system.log.helper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.system.log.helper.LogHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс реализующий методы работы с log.
 *
 * @author Krylov Sergey (25.07.2020)
 */
@Service
public class LogHelperImpl implements LogHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogHelperImpl.class);
	private static final String SEPARATOR = System.getProperty("line.separator");
	private static final String MESSAGE = "Не задано системное свойство для имени файла лога. " +
			"Возможно логгирование в файл было отключено.";


	private final String logFileName;

	public LogHelperImpl(@Value("${logging.file.name}") String logFileName) {
		this.logFileName = logFileName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLogs(Long lines) {
		if (logFileName == null || logFileName.isEmpty()) {
			LOGGER.error(MESSAGE);
			return MESSAGE;
		}

		if (lines == null) {
			return getAllLogFile(logFileName);
		}
		return getLastLogLines(logFileName, lines);
	}

	/**
	 * Вспомогательный метод для вывода всего файла логов.
	 *
	 * @param path Имя файла
	 * @return Логи
	 */
	private String getAllLogFile(String path) {
		// Это не лучший вариант, но сойдет
		var stringBuilder = new StringBuilder();

		// Это более менее эффективно
		try (var inputStream = new FileInputStream(path);
			 var sc = new Scanner(inputStream, "UTF-8")) {
			while (sc.hasNextLine()) {
				stringBuilder.append(sc.nextLine());
				stringBuilder.append(SEPARATOR);
			}
			// обратите внимание, что сканер подавляет исключения
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
			LOGGER.info("Successful display of all logs.");
			return stringBuilder.toString();
		} catch (IOException e) {
			var message = String.format("%s path = %s", e.getClass(), path);
			LOGGER.error(message);
			return message;
		}
	}

	/**
	 * Вспомогательный метод для вывода n последних строк лога.
	 *
	 * @param path  Имя файла
	 * @param lines Кол-во строк
	 * @return n последних строк файла
	 */
	private String getLastLogLines(String path, Long lines) {
		var linesList = new ArrayList<String>();

		// Это не лучший вариант, но сойдет
		var stringBuilder = new StringBuilder();

		// Это более менее эффективно
		try (var inputStream = new FileInputStream(path);
			 var sc = new Scanner(inputStream, "UTF-8")) {
			while (sc.hasNextLine()) {
				linesList.add(sc.nextLine());
			}

			int startIndex = linesList.size() - lines.intValue();
			for (int i = startIndex; i < linesList.size(); i++) {
				stringBuilder.append(linesList.get(i));
				stringBuilder.append(SEPARATOR);
			}

			// обратите внимание, что сканер подавляет исключения
			if (sc.ioException() != null) {
				throw sc.ioException();
			}

			var message = String.format("Successful display of %d rows of logs", lines);
			LOGGER.info(message);
			return stringBuilder.toString();
		} catch (IOException e) {
			var message = String.format("%s path = %s", e.getClass(), path);
			LOGGER.error(message);
			return message;
		}
	}
}
