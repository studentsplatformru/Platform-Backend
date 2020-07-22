package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class StudentCouncilDTO extends BaseDTO implements Serializable {

    private String phone;
    private String email;
    private String audience;
    private String vkGroup;
    private Long facultyId;

}