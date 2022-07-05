package hu.wurfel.refference.school.mark;

import lombok.Data;

@Data
public class MarkDto {
    private final Long id;
    private final Integer diaryId;
    private final String date;
    private final Integer subjectId;
    private final byte mark;
}
