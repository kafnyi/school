package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiarySubjectTeacherStudentService extends CrudService<DiarySubjectTeacherStudent,DiarySubjectTeacherStudentRepository> {

	@Autowired
	public DiarySubjectTeacherStudentService(DiarySubjectTeacherStudentRepository repository) {
		super(repository);
	}



}
