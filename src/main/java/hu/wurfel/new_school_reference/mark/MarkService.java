package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diary_subject_teacher_student.Diary_Subject_Teacher_Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class MarkService extends CrudService<Mark,MarkRepository> {

	@Autowired
	public MarkService(MarkRepository repository) {
		super(repository);
	}

	public List<Mark> findAllByTestDate(Date date){
		return repository.findAllByTestDate(date);
	}

	public List<Mark> findAllByDiarySubjectStudent(Diary_Subject_Teacher_Student diarySubjectTeacherStudent){
		return repository.findAllByDiarySubjectTeacherStudent(diarySubjectTeacherStudent);
	}

	public List<Mark> findAllByValue(short value){
		return repository.findAllByValue(value);
	}

	public List<Mark> findAllByMarkModifier(MarkModifier markModifier){
		return repository.findAllByMarkModifier(markModifier);
	}

}
