package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.MarkModifier;
import lombok.Data;

import java.util.Base64;
import java.util.Date;

@Data
public class MarkDto  extends BaseDto {

	private Long id;
	private Date testDate;
	private Long diarySubjectTeacherStudentId;
	private short value;
	private MarkModifier markModifier;

	@Override
	public boolean hasId() {
		return id != null;
	}

	public boolean hasTestDate() {
		return testDate != null;
	}

	public boolean hasDiarySubjectTeacherStudent() {
		return diarySubjectTeacherStudentId != 0;
	}

	public boolean hasValue() {
		return value != 0;
	}

	public boolean hasMarkModifier() {
		return markModifier != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasId()
				&& !hasTestDate()
				&& !hasDiarySubjectTeacherStudent()
				&& !hasValue()
				&& !hasMarkModifier();
	}
}
