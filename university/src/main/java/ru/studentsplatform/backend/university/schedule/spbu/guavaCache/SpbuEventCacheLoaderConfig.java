package ru.studentsplatform.backend.university.schedule.spbu.guavaCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuEventService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Конфигурация кэширования занятий.
 */
@Configuration
public class SpbuEventCacheLoaderConfig {

	private final SpbuEventService service;

	public SpbuEventCacheLoaderConfig(SpbuEventService service) {
		this.service = service;
	}

	/**
	 * Возвращает механизм для работы с кэшем.
	 * Используется как обёртка над гет-методом для получения расписания.
	 * @return Обработчик кэшиованных значений
	 */
	@Bean("SpbuEventCacheLoader")
	public LoadingCache<String, List<SpbuEvent>> eventCache() {
		return CacheBuilder.newBuilder()
				.expireAfterWrite(7, TimeUnit.DAYS)
				.build(new CacheLoader<>() {
					@Override
					public List<SpbuEvent> load(String teamName) {
						return service.getWeekEvents(teamName);
					}
				});
	}

}
