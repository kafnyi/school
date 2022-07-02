package hu.wurfel.refference.school.teacher;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherDto {
    private final Long id;
    private final String name;
    private final Date birthDate;
}
