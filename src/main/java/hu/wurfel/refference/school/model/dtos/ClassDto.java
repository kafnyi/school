package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.Year;

@Data
public class ClassDto implements Serializable {
    private final Integer cId;
    private final short grade;
    private final char sign;
    private final Year year;
    private final Long tId;
}
