package ru.studentsplatform.backend.entities.model.spbu;

import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spbu_team")
public class SpbuTeam {

	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "alias")
	private String alias;

	public String getAlias() {
		return alias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
