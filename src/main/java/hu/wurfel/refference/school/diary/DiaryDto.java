package hu.wurfel.refference.school.diary;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiaryDto implements Serializable {
    private final Integer id;
    private final Long studentId;
    private final Integer classId;
}
