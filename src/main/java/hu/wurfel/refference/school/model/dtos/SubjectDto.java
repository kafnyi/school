package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectDto implements Serializable {
    private final Integer sjId;
    private final String subjectName;
    private final Long tId;
}
