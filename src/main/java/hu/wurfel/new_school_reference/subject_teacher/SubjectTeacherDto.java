package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.subject.SubjectDto;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import lombok.Data;

@Data
public class SubjectTeacherDto extends BaseDto {

	private Long id;
	private SubjectDto subject;
	private TeacherDto teacher;

	@Override
	public boolean hasId() {
		return id != null;
	}

	public boolean hasSubject() {
		return subject != null;
	}

	public boolean hasTeacher() {
		return teacher != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasId()
				&& !hasSubject()
				&& !hasTeacher();
	}

}
