package ru.studentsplatform.backend.endpoint.rest.spbu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.service.proxy.SpbuProxy;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuService;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс контроллера для получения сведений из API СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
@Profiled
@RestController
@RequestMapping("/spbu")
public class SpbuDataControllerImpl implements SpbuDataController {

	private final Logger logger = LoggerFactory.getLogger(SpbuDataControllerImpl.class);

	private final SpbuProxy proxy;
	private final SpbuService service;

	public SpbuDataControllerImpl(SpbuProxy proxy, SpbuService service) {
		this.proxy = proxy;
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<List<SpbuDivisionDTO>> getDivisions() {
//		Proxy newproxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("166.98.140.51", 3128));
//		OkHttpClient okHttpClient = new OkHttpClient.Builder().proxy(newproxy).build();
//		Feign.builder()
//				.client(new feign.okhttp.OkHttpClient(okHttpClient))
//				.target(SpbuProxy.class, "https://timetable.spbu.ru/api/v1/");
//		for (int i = 0; i < 1500; i++) {
//			try {
//				Thread.sleep(20);
//				proxy.getDivisions();
//				System.out.println((float) i / 1500f * 100f + "%");
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		return ResponseEntity.ok(proxy.getDivisions());
	}

//		HttpHost proxy = new HttpHost("166.98.140.51", 3128);
//		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//		CloseableHttpClient httpClient
//				= HttpClients.custom()
//				.setSSLHostnameVerifier(new NoopHostnameVerifier())
//				.setRoutePlanner(routePlanner)
//				.build();
//		HttpComponentsClientHttpRequestFactory requestFactory
//				= new HttpComponentsClientHttpRequestFactory();
//		requestFactory.setHttpClient(httpClient);
//		ResponseEntity<SpbuDivisionDTO[]> response
//				= new RestTemplate(requestFactory).exchange(
//				"https://timetable.spbu.ru/api/v1/study/divisions", HttpMethod.GET, null, SpbuDivisionDTO[].class);
//

//		for (int i = 0; i < 1500; i++) {
//			try {
//				Thread.sleep(10);
//				proxy.getDivisions();
//				System.out.println((float) i / 1500f * 100f + "%");
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;


	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<List<SpbuStudyProgramDTO>> getStudyPrograms(String alias) {
		try {
			return ResponseEntity.ok(service.studyProgramUnwrap(proxy.getProgramLevels(alias)));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<List<SpbuTeamDTO>> getGroups(String id) {
		try {
			return ResponseEntity.ok(proxy.getGroups(id).getGroups());
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<List<SpbuEventDTO>> getNextWeekEventsById(String id) {
		try {
			return ResponseEntity.ok(service.eventUnwrap(proxy.getDays(id).getDays()));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<List<SpbuEventDTO>> getEventsByIdForTimeInterval(String id,
																		   String startTime,
																		   String endTime) {
		try {
			return ResponseEntity.ok(service.eventUnwrap(proxy.getDays(id, startTime, endTime).getDays()));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuEventDTO>> getNextWeekEventsByName(String name) {
		try {
			var id = service.getByName(name).getId().toString();
			return ResponseEntity.ok(service.eventUnwrap(proxy.getDays(id).getDays()));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuEventDTO>> getEventsByNameForTimeInterval(String name,
																			 String startTime,
																			 String endTime) {
		try {
			var id = service.getByName(name).getId().toString();
			return ResponseEntity.ok(service.eventUnwrap(proxy.getDays(id, startTime, endTime).getDays()));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<String> saveAllGroupsToDB(String alias) {
		service.saveAllAliasGroups(alias);
		return ResponseEntity.ok("Groups saving started for alias: " + alias);
	}
}
