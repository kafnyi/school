package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.subject.Subject;
import hu.wurfel.new_school_reference.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {
    List<SubjectTeacher> findAllBySubject(Subject subject);

    List<SubjectTeacher> findAllByTeacher(Teacher teacher);

    SubjectTeacher findBySubjectAndTeacher(Subject subject, Teacher teacher);
}
