package hu.wurfel.refference.school.teacher;

import lombok.Data;

@Data
public class TeacherDto {
    private final Long id;
    private final String name;
    private final String birthDate;
}
