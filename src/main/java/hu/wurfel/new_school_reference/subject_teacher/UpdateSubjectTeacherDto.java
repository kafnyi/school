package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.Dto;
import lombok.Data;

@Data
public class UpdateSubjectTeacherDto extends BaseDto {

	private Long subjectId;
	private Long teacherId;

	public boolean hasValidSubjectId(){
		return subjectId != null
				&& subjectId > 0;
	}

	public boolean hasValidTeacherId(){
		return teacherId != null
				&& teacherId > 0;
	}

	@Override
	public boolean isEmpty() {
		return !this.hasValidSubjectId()
				&& !this.hasValidTeacherId();
	}

}
