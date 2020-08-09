package ru.studentsplatform.backend.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.studentsplatform.backend.domain.dto.spbu.DivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.ProgramLevelDTO;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@FeignClient(name = "getDivision", url = "https://timetable.spbu.ru/api/v1/study/divisions/")
public interface DivisionProxy {
	@GetMapping()
	List<DivisionDTO> getDivisions();

	@GetMapping("/{alias}/programs/levels")
	List<ProgramLevelDTO> getProgramLevels(@PathVariable(name = "alias") String alias);
}
