package ru.studentsplatform.backend.entities.model.spbu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

class SpbuEventId implements Serializable {
	private String team;
	private LocalDate date;
	private LocalTime startTime;
}

@Entity
@Table(name = "spbu_event")
@IdClass(SpbuEventId.class)
public class SpbuEvent {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team")
	private SpbuTeam team;

	@Id
	@Column(name = "date")
	private LocalDate date;

	@Id
	@Column(name = "startTime")
	private LocalTime startTime;

	@Column(name = "endTime")
	private LocalTime endTime;

	@Column(name = "subject")
	private String subject;

	@Column(name = "location")
	private String location;

	@Column(name = "educator")
	private String educator;

	public SpbuTeam getTeam() {
		return team;
	}

	public void setTeam(SpbuTeam team) {
		this.team = team;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEducator() {
		return educator;
	}

	public void setEducator(String educator) {
		this.educator = educator;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
