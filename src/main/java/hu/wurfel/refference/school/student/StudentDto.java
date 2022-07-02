package hu.wurfel.refference.school.student;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDto {
    private final Long id;
    private final String name;
    private final Date birthDate;
}
