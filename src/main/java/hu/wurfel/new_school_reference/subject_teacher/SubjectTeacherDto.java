package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.subject.SubjectDto;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import lombok.Data;

@Data
public class SubjectTeacherDto {

	private Long id;
	private SubjectDto subject;
	private TeacherDto teacher;

	public boolean hasId() {
		return id != null;
	}

	public boolean hasSubject() {
		return subject != null;
	}

	public boolean hasTeacher() {
		return teacher != null;
	}

	public boolean isEmpty() {
		return id == null
				&& subject == null
				&& teacher == null;
	}

}
