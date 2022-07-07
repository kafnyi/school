package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diary_subject_teacher_student.DiarySubjectTeacherStudent;
import lombok.Data;

import java.util.Date;

@Data
public class MarkDto {

	private Long id;

	private Date testDate;

	private DiarySubjectTeacherStudent diarySubjectTeacherStudent;

	private short value;

	private MarkModifier markModifier;
}
