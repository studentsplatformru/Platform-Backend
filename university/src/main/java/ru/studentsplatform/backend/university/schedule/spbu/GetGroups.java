package ru.studentsplatform.backend.university.schedule.spbu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.studentsplatform.backend.domain.dto.spbu.GroupsDTO;
import ru.studentsplatform.backend.service.proxy.GroupProxy;

import java.util.List;

@RestController
@RequestMapping("/spbu/groups/")
public class GetGroups {

	private final GroupProxy proxy;

	public GetGroups(GroupProxy proxy) {
		this.proxy = proxy;
	}

	@GetMapping("{id}")
	public List<GroupsDTO.GroupDTO> getGroups(@PathVariable(name = "id") String id) {
		System.out.println(proxy.getGroups(id).getGroups());
		return proxy.getGroups(id).getGroups();
	}


}
