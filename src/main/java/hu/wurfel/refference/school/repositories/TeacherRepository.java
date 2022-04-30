package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

	ArrayList<Teacher> findAll();

	Optional<Teacher> findByTID(Long id);

	ArrayList<Teacher> findAllByName(String name);

	ArrayList<Teacher> findAllByBirthDate(Date birthDate);

	Optional<Teacher> findByNameAndBirthDate(String name, Date birthDate);
}
