package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	ArrayList<Student> findAll();

	Student findBySID(long Id);

	Student findByName(String Name);

	Student findByBirthDate(String Birth);

}
