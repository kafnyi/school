package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.division.Class;
import hu.wurfel.new_school_reference.teacher.Teacher;
import lombok.Data;
import net.bytebuddy.implementation.bytecode.Division;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class DiaryDto {

	private Long id;

	private Date start;

	private Date end;

	private Division division;

	private Teacher headTeacher;
}
