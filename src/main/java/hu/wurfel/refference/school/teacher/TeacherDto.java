package hu.wurfel.refference.school.teacher;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TeacherDto implements Serializable {
    private final Long id;
    private final String name;
    private final Date birthDate;
}
