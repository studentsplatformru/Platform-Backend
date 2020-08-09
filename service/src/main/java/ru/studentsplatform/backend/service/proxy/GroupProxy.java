package ru.studentsplatform.backend.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ru.studentsplatform.backend.domain.dto.spbu.GroupsDTO;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

@Profiled
@FeignClient(name = "getGroups", url = "https://timetable.spbu.ru/api/v1/progams/")
public interface GroupProxy {
	@GetMapping("{id}/groups")
	GroupsDTO getGroups(@PathVariable(name = "id") String id);
}
