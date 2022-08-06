package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.Dto;
import hu.wurfel.new_school_reference.base.MarkModifier;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMarkDtoWithConnectId implements Dto {

	private LocalDate testDate;
	private Long diarySubjectTeacherStudentId;
	private short value;
	private MarkModifier markModifier;

	public boolean hasTestDate(){
		return testDate != null;
	}

	public boolean hasValidDiarySubjectTeacherStudentId(){
		return diarySubjectTeacherStudentId != null
				&& diarySubjectTeacherStudentId > 0;
	}

	public boolean hasValidValue(){
		return value >= 1
				&& value <= 5;
	}

	public boolean hasMarkModifier(){
		return markModifier != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasTestDate()
				&& !hasValidDiarySubjectTeacherStudentId()
				&& !hasValidValue()
				&& !hasMarkModifier();
	}

}
