package hu.wurfel.refference.school.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findAllByName(String Name);

	List<Student> findAllByBirthDate(String birthDate);

    Optional<Student> findByNameAndBirthDate(String name, String birthDate);
}
