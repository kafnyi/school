package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	ArrayList<Student> findAll();

	Optional<Student> findById(Long SID);

	ArrayList<Student> findAllByName(String Name);

	ArrayList<Student> findAllByBirthDate(String birthDate);

	Optional<Student> findByNameAndBirthDate(String name, String birthDate);
}
