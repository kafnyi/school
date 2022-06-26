package hu.wurfel.refference.school.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllByName(String name);

    List<Teacher> findAllByBirthDate(String birthDate);

    Optional<Teacher> findByNameAndBirthDate(String name, Date birthDate);
}
