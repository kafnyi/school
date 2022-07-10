package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.MarkModifier;
import lombok.Data;

import java.util.Date;

@Data
public class MarkDto {

	private Long id;
	private Date testDate;
	private long diarySubjectTeacherStudentId;
	private short value;
	private MarkModifier markModifier;

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

	public boolean isEmpty() {
		return id == null
				&& testDate == null
				&& diarySubjectTeacherStudentId == 0
				&& value == 0
				&& markModifier == null;
	}
}
