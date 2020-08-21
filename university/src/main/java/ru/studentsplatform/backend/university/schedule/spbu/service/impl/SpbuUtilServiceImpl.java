package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.proxy.FeignConfig;
import ru.studentsplatform.backend.service.proxy.SpbuProxy;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUtilService;

/**
 * Имплементация сервиса, содержащего утилитные методы для работы с БД СПБГУ.
 */
@Slf4j
@Service
@Profiled
public class SpbuUtilServiceImpl implements SpbuUtilService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean stressTest(int iterations) {
		SpbuProxy proxy = FeignConfig.getSpbuProxy();
		int counter = 0;
		try {
			while (counter < iterations) {
				proxy.getDivisions();
				counter++;
				log.info("Successful request for iteration №{}", counter);
			}
			return true;
		} catch (Exception e) {
			log.warn("Stress test request failed on iteration №{}, exception: {}", counter, e.getLocalizedMessage());
			return false;
		}
	}

}
