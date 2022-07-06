package hu.wurfel.new_school_reference.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByName(String name);

    List<Teacher> findAllByBirthDate(Date date);

    List<Teacher> findAllByTeacherCardNumber(Long cardNumber);
}
