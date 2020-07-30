package ru.studentsplatform.backend.entities.model.user;

import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {
	@OneToOne(fetch = FetchType.LAZY)
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

	/**
	 * Поле имя фото.
	 */
	@Column(name = "img_name")
	private String imgName;

	/**
	 * Поле расширение фото.
	 */
	@Column(name = "img_type")
	private String imgType;

	/**
	 * Поле файл фото.
	 */
	@Lob
	@Column(name = "img")
	private byte[] img;

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
}
