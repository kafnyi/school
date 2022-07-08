package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.student.StudentDto;
import hu.wurfel.new_school_reference.subject_teacher.SubjectTeacherDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiarySubjectTeacherStudentDto {

	private long id;

	private LocalDateTime updatedAt;

	private long diaryId;

	private StudentDto student;

	private SubjectTeacherDto subjectTeacherDto;


}
