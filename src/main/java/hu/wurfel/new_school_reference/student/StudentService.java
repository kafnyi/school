package hu.wurfel.new_school_reference.student;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class StudentService extends CrudService<Student,StudentRepository> {

	@Autowired
	public StudentService(StudentRepository repository) {
		super(repository);
	}

	public List<Student> findAllByName (String name){
		return repository.findAllByName(name);
	}

	public List<Student> findAllByBirthDate(Date date){
		return repository.findAllByBirthDate(date);
	}

	public Student findByCardNumber(long number){
		return repository.findByCardNumber(number);
	}
}
