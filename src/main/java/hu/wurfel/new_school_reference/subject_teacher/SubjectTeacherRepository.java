package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.subject.Subject;
import hu.wurfel.new_school_reference.teacher.Teacher;

import java.util.List;

public class SubjectTeacherRepository extends CrudService<Subject_Teacher,Subject_TeacherRepository> {

	public SubjectTeacherRepository(Subject_TeacherRepository repository) {
		super(repository);
	}

	public List<Subject_Teacher> findAllBySubject(Subject subject){
		return repository.findAllBySubject(subject);
	}

	public List<Subject_Teacher> findAllByTeacher(Teacher teacher){
		return repository.findAllByTeacher(teacher);
	}

	public Subject_Teacher findBySubjectAndTeacher(Subject subject, Teacher teacher){
		return repository.findBySubjectAndTeacher(subject,teacher);
	}
}
