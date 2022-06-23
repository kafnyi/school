package hu.wurfel.refference.school.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    ArrayList<Teacher> findAllByName(String name);

    ArrayList<Teacher> findAllByBirthDate(String birthDate);

    Optional<Teacher> findByNameAndBirthDate(String name, Date birthDate);
}
