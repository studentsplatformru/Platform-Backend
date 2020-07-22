package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class LessonUnitDTO extends BaseDTO implements Serializable {

    private OffsetTime startTime;
    private OffsetTime endTime;
    private String audience;
    private String type;
    private String note;
    private Long teacherId;
    private Long subjectId;

    private List<LessonDTO> lessonsDTO;
}
