package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudent;
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
