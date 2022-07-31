package hu.wurfel.new_school_reference.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByNameAndDeleted(String name, boolean deleted);

    List<Student> findAllByBirthDateAndDeleted(LocalDate date, boolean deleted);

    Student findByCardNumberAndDeleted(long cardNumber, boolean deleted);

}
