package ru.studentsplatform.backend.university.schedule.spbu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.spbu.DivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.ProgramLevelDTO;
import ru.studentsplatform.backend.service.proxy.DivisionProxy;

import java.util.List;
@RestController
@RequestMapping("/spbu/divisions")
public class GetDivisions {

	private final DivisionProxy proxy;

	public GetDivisions(DivisionProxy proxy) {
		this.proxy = proxy;
	}

	@GetMapping
	List<DivisionDTO> getDivisions() {

		return proxy.getDivisions();
	}

	@GetMapping("/levels/{alias}")
	List<ProgramLevelDTO> getProgramLevels(@PathVariable(name = "alias") String alias) {

		return proxy.getProgramLevels(alias);
	}
}
