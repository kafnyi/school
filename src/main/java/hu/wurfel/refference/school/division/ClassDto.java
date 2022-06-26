package hu.wurfel.refference.school.division;

import lombok.Data;

import java.io.Serializable;
import java.time.Year;

@Data
public class ClassDto implements Serializable {
    private final Integer id;
    private final short grade;
    private final char sign;
    private final Year year;
    private final Long teacherId;
}
