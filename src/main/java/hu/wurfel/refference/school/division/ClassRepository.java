package hu.wurfel.refference.school.division;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

	List<Class> findAllByGrade(short grade);

	List<Class> findAllBySign(char sign);

	List<Class> findAllByYear(Year year);

	List<Class> findAllByTeacherId(Long teacherId);

	List<Class> findAllByGradeAndSign(short grade, char sign);

	List<Class> findAllByGradeAndYear(short grade, Year year);

	List<Class> findAllBySignAndYear(char sign, Year year);

    Optional<Class> findByGradeAndSignAndYear(short grade, char sign, Year year);
}
