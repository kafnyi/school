package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepository  extends CrudRepository<Student, Long> {
	List<Student> FindAll();
	Student FindById(Long Id);


}
