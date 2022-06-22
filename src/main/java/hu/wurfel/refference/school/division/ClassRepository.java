package hu.wurfel.refference.school.division;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ClassRepository extends CrudRepository<Class, Integer> {
    ArrayList<Class> findAll();

    Optional<Class> findById(Integer classId);

    ArrayList<Class> findAllByGrade(short grade);

    ArrayList<Class> findAllBySign(char sign);

    ArrayList<Class> findAllByYear(Year year);

    ArrayList<Class> findAllByTeacherId(Long teacherId);

    ArrayList<Class> findAllByGradeAndSign(short grade, char sign);

    ArrayList<Class> findAllByGradeAndYear(short grade, Year year);

    ArrayList<Class> findAllBySignAndYear(char sign, Year year);

    Optional<Class> findByGradeAndSignAndYear(short grade, char sign, Year year);
}