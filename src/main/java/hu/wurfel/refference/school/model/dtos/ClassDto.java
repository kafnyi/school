package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClassDto implements Serializable {
	private final Integer cId;
	private final Integer grade;
	private final Character sign;
	private final Integer year;
	private final Long tId;
}
