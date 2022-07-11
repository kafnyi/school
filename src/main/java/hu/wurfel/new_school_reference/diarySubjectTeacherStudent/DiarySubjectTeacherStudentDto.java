package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.diary.DiaryDto;
import hu.wurfel.new_school_reference.student.StudentDto;
import hu.wurfel.new_school_reference.subject_teacher.SubjectTeacherDto;
import lombok.Data;

@Data
public class DiarySubjectTeacherStudentDto extends BaseDto {

	private Long id;
	private DiaryDto diary;
	private StudentDto student;
	private SubjectTeacherDto subjectTeacher;

	@Override
	public boolean hasId() {
		return id != null;
	}

	public boolean hasDiary() {
		return diary != null;
	}

	public boolean hasStudent() {
		return student != null;
	}

	public boolean hasSubjectTeacher() {
		return subjectTeacher != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasId()
				&& !hasDiary()
				&& !hasStudent()
				&& !hasSubjectTeacher();
	}

}
