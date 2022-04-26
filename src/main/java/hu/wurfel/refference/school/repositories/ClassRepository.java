package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Class;
import org.springframework.data.repository.CrudRepository;

import java.time.Year;
import java.util.ArrayList;
import java.util.Optional;

public interface ClassRepository extends CrudRepository<Class, Integer> {
	ArrayList<Class> findAll();

	Optional<Class> findByClassID(Integer classId);

	ArrayList<Class> findAllByGrade(short grade);

	ArrayList<Class> findAllBySign(char sign);

	ArrayList<Class> findAllByYear(Year year);

	Optional<Class> findByTid(Integer tId);

	ArrayList<Class> findAllByGradeAndSign(short grade, char sign);

	ArrayList<Class> findAllByGradeAndYear(short grade, Year year);

	Optional<Class> findAllByGradeAndSignAndYear(short grade, char sign, Year year);
}
