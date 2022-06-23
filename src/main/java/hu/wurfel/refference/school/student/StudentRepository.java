package hu.wurfel.refference.school.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    ArrayList<Student> findAllByName(String Name);

    ArrayList<Student> findAllByBirthDate(String birthDate);

    Optional<Student> findByNameAndBirthDate(String name, String birthDate);
}
