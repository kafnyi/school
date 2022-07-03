package hu.wurfel.refference.school.subject;

import lombok.Data;

@Data
public class SubjectDto {
	private final Integer id;
	private final String subjectName;
	private final Long teacherId;
}
