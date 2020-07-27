package ru.studentsplatform.backend.domain.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;

/**
 * Основа для создания объектов DTO. Содержит три базовых поля, которые будут использованы в дочерних классах.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@Data
@MappedSuperclass
public abstract class BaseDTO {

	private Long id;

	private String createdBy;

	private String modifiedBy;
}
