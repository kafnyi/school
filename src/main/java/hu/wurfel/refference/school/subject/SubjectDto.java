package hu.wurfel.refference.school.subject;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectDto implements Serializable {
	private final Integer id;
	private final String name;
	private final Long teacherId;
}
