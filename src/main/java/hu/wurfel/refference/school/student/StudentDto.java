package hu.wurfel.refference.school.student;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentDto implements Serializable {
    private final Long id;
    private final String name;
    private final Date birthDate;
}
