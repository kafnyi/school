package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.CrudService;

import java.util.Date;
import java.util.List;

public class TeacherService extends CrudService<Teacher,TeacherRepository> {

	public TeacherService(TeacherRepository repository) {
		super(repository);
	}

	public List<Teacher> findAllByName(String name){
		return repository.findAllByName(name);
	}

	public List<Teacher> findAllByBirthDate(Date date){
		return repository.findAllByBirthDate(date);
	}

	public Teacher findByTeacherCardNumber(long number){
		return repository.findAllByCardNumber(number);
	}
}
