package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MarkService extends CrudService<Mark,MarkRepository> {

	@Autowired
	public MarkService(MarkRepository repository) {
		super(repository);
	}

	public List<Mark> findAllByTestDate(Date date){
		return repo.findAllByTestDate(date);
	}

	public List<Mark> findAllByDiarySubjectStudent(DiarySubjectTeacherStudent diarySubjectTeacherStudent){
		return repo.findAllByDiarySubjectTeacherStudent(diarySubjectTeacherStudent);
	}

	public List<Mark> findAllByValue(short value){
		return repo.findAllByValue(value);
	}

	public List<Mark> findAllByMarkModifier(MarkModifier markModifier){
		return repo.findAllByMarkModifier(markModifier);
	}

}
