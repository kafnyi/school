package hu.wurfel.new_school_reference.student;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService extends CrudService<Student,StudentRepository> {

	@Autowired
	public StudentService(StudentRepository repository) {
		super(repository);
	}

	public List<Student> findAllByName (String name){
		return repo.findAllByName(name);
	}

	public List<Student> findAllByBirthDate(Date date){
		return repo.findAllByBirthDate(date);
	}

	public Student findByCardNumber(long number){
		return repo.findByCardNumber(number);
	}
}
