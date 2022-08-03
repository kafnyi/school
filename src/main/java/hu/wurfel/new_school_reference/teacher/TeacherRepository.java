package hu.wurfel.new_school_reference.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByNameAndDeleted(String name, boolean deleted);

    List<Teacher> findAllByBirthDateAndDeleted(LocalDate date, boolean deleted);

    Teacher findAllByCardNumberAndDeleted(Long cardNumber, boolean deleted);
}
