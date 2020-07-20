package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * Класс всех пользователей.
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

	/**
	 * Поле имя.
	 */
	@Column(name = "first_name", nullable = false)
	private String firstName;

	/**
	 * Поле фамилия.
	 */
	@Column(name = "last_name", nullable = false)
	private String lastName;

	/**
	 * Поле отчество.
	 */
	@Column(name = "patronymic", nullable = false)
	private String patronymic;

	/**
	 * Поле номер телефона.
	 */
	@Column(name = "phone", nullable = false)
	private String phoneNumber;

	/**
	 * Поле электронная почта.
	 */
	@Column(name = "email", nullable = false)
	private String email;

	/**
	 * Поле пароль.
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * Связь "один-к-одному" - Преподаватель.
	 */
	@OneToOne(mappedBy = "user")
	private Teacher teacher;

	/**
	 * Связь "один-к-одному" - Студент.
	 */
	@OneToOne(mappedBy = "user")
	private Student student;

	/**
	 * Связь "один-ко-многим" - Обратная связь с преподавателем.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<TeachersFeedback> teachersFeedback;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Set<TeachersFeedback> getTeachersFeedback() {
		return teachersFeedback;
	}

	public void setTeachersFeedback(Set<TeachersFeedback> teachersFeedback) {
		this.teachersFeedback = teachersFeedback;
	}
}
