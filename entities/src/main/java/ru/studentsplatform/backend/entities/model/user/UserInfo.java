package ru.studentsplatform.backend.entities.model.user;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {
	@OneToOne
	@MapsId
	private User user;

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
}
