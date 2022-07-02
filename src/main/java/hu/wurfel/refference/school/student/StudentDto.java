package hu.wurfel.refference.school.student;

import lombok.Data;

@Data
public class StudentDto {
    private final Long id;
    private final String name;
    private final String birthDate;
}
