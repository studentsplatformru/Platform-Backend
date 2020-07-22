package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class StudentDTO extends BaseDTO implements Serializable {

    private Long userId;
    private Long departmentId;
    private Long teamId;
    private Long directionId;

    private List<MarkDTO> marksDTO;

}