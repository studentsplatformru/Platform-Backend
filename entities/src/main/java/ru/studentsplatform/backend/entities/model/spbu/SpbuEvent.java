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
import java.sql.Timestamp;

class SpbuEventId implements Serializable {
	private String dayWithTimeInterval;
	private String team;
}

@Entity
@Table(name = "spbu_event")
@IdClass(SpbuEventId.class)
public class SpbuEvent {

	@Column(name = "subject")
	private String subject;

	@Id
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

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team")
	private SpbuTeam team;

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
