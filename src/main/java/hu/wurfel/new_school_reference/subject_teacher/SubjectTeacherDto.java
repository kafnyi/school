package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

@Data
public class SubjectTeacherDto extends BaseDto {

	private Long subjectId;
	private Long teacherId;

	public SubjectTeacherDto(SubjectTeacher subjectTeacher){
		super(subjectTeacher.getId(),
				subjectTeacher.isDeleted(),
				subjectTeacher.getCreatedAt(),
				subjectTeacher.getUpdatedAt());
		this.subjectId = subjectTeacher.getSubject().getId();
		this.teacherId = subjectTeacher.getTeacher().getId();
	}

	public boolean hasValidSubjectId() {
		return subjectId != null
				&& subjectId > 0;
	}

	public boolean hasValidTeacherId() {
		return teacherId != null
				&& teacherId > 0;
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId()
				&& !hasValidSubjectId()
				&& !hasValidTeacherId();
	}

}
