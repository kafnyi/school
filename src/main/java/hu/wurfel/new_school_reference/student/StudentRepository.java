package hu.wurfel.new_school_reference.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByNameAndDeletedIsFalse(String name);

    List<Student> findAllByBirthDateAndDeletedIsFalse(Date date);

    Student findByCardNumberAndDeletedIsFalse(long cardNumber);

}
