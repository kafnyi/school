package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import lombok.Data;

import java.util.Date;

@Data
public class DiaryDto {

	private Long id;

	private Date start;

	private Date end;

	private ClassDto classDto;

	private TeacherDto headTeacher;
}
