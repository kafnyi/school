package hu.wurfel.refference.school.mark;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MarkDto implements Serializable {
    private final Long id;
    private final Integer diaryId;
    private final Date date;
    private final Integer subjectId;
    private final byte mark;
}
