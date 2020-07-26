package ru.studentsplatform.backend.domain.dto;

import java.util.Date;

/**
 * Класс DTO, хранящий сведения о сущности студенчекой задачи.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
public class TaskDTO extends ru.studentsplatform.backend.domain.dto.BaseDTO {

    private String taskName;

    private Date deadLine;

    private boolean isDone;

    private Integer mark;

    private Long scheduleUserCellId;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Long getScheduleUserCellId() {
        return scheduleUserCellId;
    }

    public void setScheduleUserCellId(Long scheduleUserCellId) {
        this.scheduleUserCellId = scheduleUserCellId;
    }
}
