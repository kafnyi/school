package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.subject.Subject;
import hu.wurfel.new_school_reference.teacher.Teacher;

import java.util.List;

public class SubjectTeacherService extends CrudService<SubjectTeacher, SubjectTeacherRepository> {

	public SubjectTeacherService(SubjectTeacherRepository repository) {
		super(repository);
	}

	public List<SubjectTeacher> findAllBySubject(Subject subject){
		return repository.findAllBySubject(subject);
	}

	public List<SubjectTeacher> findAllByTeacher(Teacher teacher){
		return repository.findAllByTeacher(teacher);
	}

	public SubjectTeacher findBySubjectAndTeacher(Subject subject, Teacher teacher){
		return repository.findBySubjectAndTeacher(subject,teacher);
	}
}
