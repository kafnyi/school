package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.Dto;
import lombok.Data;

@Data
public class CreateDiarySubjectTeacherStudentDto implements Dto {

	private Long diaryId;
	private Long subjectTeacherId;
	private Long studentId;

	public boolean hasValidDiaryId(){
		return diaryId != null
				&& diaryId > 0;
	}

	public boolean hasValidSubjectTeacherId(){
		return subjectTeacherId != null
				&& subjectTeacherId > 0;
	}

	public boolean hasValidStudentId(){
		return studentId != null
				&& studentId > 0;
	}

	@Override
	public boolean isEmpty() {
		return !this.hasValidDiaryId()
				&& !this.hasValidSubjectTeacherId()
				&& !this.hasValidStudentId();
	}
}
