package hu.wurfel.new_school_reference.diary_subject_teacher_student;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiarySubjectTeacherStudentService extends CrudService<Diary_Subject_Teacher_Student,DiarySubjectTeacherStudentRepository> {

	@Autowired
	public DiarySubjectTeacherStudentService(DiarySubjectTeacherStudentRepository repository) {
		super(repository);
	}



}
