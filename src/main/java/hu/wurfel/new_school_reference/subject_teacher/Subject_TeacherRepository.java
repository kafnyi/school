package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.subject.Subject;
import hu.wurfel.new_school_reference.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Subject_TeacherRepository extends JpaRepository<Subject_teacher, Long> {
    List<Subject_teacher> findAllBySubject(Subject subject);

    List<Subject_teacher> findAllByTeacher(Teacher teacher);
}
