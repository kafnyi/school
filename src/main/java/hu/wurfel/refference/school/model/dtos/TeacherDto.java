package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TeacherDto implements Serializable {
	private final Long tId;
	private final String name;
	private final Date birthDate;
}
