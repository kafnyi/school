package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.MarkModifier;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MarkDto  extends BaseDto {

	private LocalDate testDate;
	private Long diarySubjectTeacherStudentId;
	private short value;
	private MarkModifier markModifier;

	public MarkDto(Mark mark){
		super(mark.getId(),
				mark.isDeleted(),
				mark.getCreatedAt(),
				mark.getUpdatedAt());
		this.testDate = mark.getTestDate();
		this.diarySubjectTeacherStudentId = mark.getDiarySubjectTeacherStudent().getId();
		this.value = mark.getValue();
		this.markModifier = mark.getMarkModifier();
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
		return !hasValidId()
				&& !hasTestDate()
				&& !hasDiarySubjectTeacherStudent()
				&& !hasValue()
				&& !hasMarkModifier();
	}
}
