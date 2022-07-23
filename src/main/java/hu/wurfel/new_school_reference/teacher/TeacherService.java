package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherService extends CrudService<Teacher,TeacherRepository> {

	public TeacherService(TeacherRepository repository) {
		super(repository);
	}

	public List<Teacher> findAllByName(String name){
		return repo.findAllByNameAndDeletedIsFalse(name);
	}

	public List<Teacher> findAllByBirthDate(Date date){
		return repo.findAllByBirthDateAndDeletedIsFalse(date);
	}

	public List<Teacher> findByTeacherCardNumber(long number){
		return this.toList(repo.findAllByCardNumberAndDeletedIsFalse(number));
	}
}
