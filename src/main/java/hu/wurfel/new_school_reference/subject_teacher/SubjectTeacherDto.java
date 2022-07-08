package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.subject.SubjectDto;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubjectTeacherDto {

	private long id;

	private LocalDateTime updatedAt;

	private SubjectDto subject;

	private TeacherDto teacher;

}
