package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.Dto;
import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.CreateDiarySubjectTeacherStudentDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMarkDtoWithDiarySubjectTeacherStudentDtoWithId implements Dto {

	private LocalDate testDate;
	private CreateDiarySubjectTeacherStudentDto diarySubjectTeacherStudentDto;
	private short value;
	private MarkModifier markModifier;

	public boolean hasTestDate(){
		return testDate != null;
	}

	public boolean hasValidDiarySubjectTeacherStudent(){
		return diarySubjectTeacherStudentDto != null &&
				diarySubjectTeacherStudentDto.hasValidDiaryId() &&
				diarySubjectTeacherStudentDto.hasValidSubjectTeacherId() &&
				diarySubjectTeacherStudentDto.hasValidStudentId();
	}

	public boolean hasValidValue(){
		return value > 0 &&
				value < 6;
	}

	public boolean hasMarkModifier(){
		return markModifier != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasTestDate() &&
				!hasValidDiarySubjectTeacherStudent() &&
				!hasValidValue() &&
				!hasMarkModifier();
	}
}
