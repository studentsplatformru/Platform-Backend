package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class LessonDTO extends BaseDTO implements Serializable {

    private OffsetDateTime date;
    private Long teamId;
    private Long lessonUnitId;

    private List<HomeworkDTO> homeworkDTO;
    private List<MarkDTO> marksDTO;
}
