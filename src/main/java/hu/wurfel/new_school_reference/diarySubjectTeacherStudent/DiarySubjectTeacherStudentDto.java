package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.diary.DiaryDto;
import hu.wurfel.new_school_reference.student.StudentDto;
import hu.wurfel.new_school_reference.subject_teacher.SubjectTeacherDto;
import lombok.Data;

@Data
public class DiarySubjectTeacherStudentDto extends BaseDto {

	private Long diaryId;
	private Long studentId;
	private Long subjectTeacherId;

	public DiarySubjectTeacherStudentDto(DiarySubjectTeacherStudent entity){
		super(entity.getId(),
				entity.isDeleted(),
				entity.getCreatedAt(),
				entity.getUpdatedAt());
		this.diaryId = entity.getDiary().getId();
		this.subjectTeacherId = entity.getSubjectTeacher().getId();
		this.studentId = entity.getStudent().getId();
	}

	public boolean hasDiaryId() {
		return diaryId != null;
	}

	public boolean hasStudentId() {
		return studentId != null;
	}

	public boolean hasSubjectTeacherId() {
		return subjectTeacherId != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId()
				&& !hasDiaryId()
				&& !hasStudentId()
				&& !hasSubjectTeacherId();
	}

}
