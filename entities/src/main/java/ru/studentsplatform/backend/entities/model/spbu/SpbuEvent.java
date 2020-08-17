package ru.studentsplatform.backend.entities.model.spbu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "spbu_event")
public class SpbuEvent {

	@Id
	private Long id;

	@Column(name = "subject")
	private String subject;

	@Column(name = "dayWithTimeInterval")
	private String dayWithTimeInterval;

	@Column(name = "timeInterval")
	private String timeInterval;

	@Column(name = "location")
	private String location;

	@Column(name = "educator")
	private String educator;

	@Column(name = "startTime")
	private Timestamp startTime;

	@Column(name = "endTime")
	private Timestamp endTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team", referencedColumnName = "name")
	private SpbuTeam team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDayWithTimeInterval() {
		return dayWithTimeInterval;
	}

	public void setDayWithTimeInterval(String dayWithTimeInterval) {
		this.dayWithTimeInterval = dayWithTimeInterval;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
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

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public SpbuTeam getTeam() {
		return team;
	}

	public void setTeam(SpbuTeam team) {
		this.team = team;
	}
}
