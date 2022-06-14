package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentDto implements Serializable {
    private final Long sId;
    private final String name;
    private final Date birthDate;
}
