package ru.studentsplatform.backend.domain.dto;

/**
 * Основа для создания объектов DTO. Содержит три базовых поля, которые будут использованы в дочерних классах.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
public abstract class BaseDTO {

    private Long id;

    private String createdBy;

    private String modifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
