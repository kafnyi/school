package hu.wurfel.refference.school.diary;

import lombok.Data;

@Data
public class DiaryDto {
    private final Integer id;
    private final Long studentId;
    private final Integer classId;
}
