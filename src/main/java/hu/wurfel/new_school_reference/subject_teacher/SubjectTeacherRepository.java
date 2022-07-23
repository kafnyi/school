package hu.wurfel.new_school_reference.subject_teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {
    List<SubjectTeacher> findAllBySubject_IdAndDeletedIsFalse(Long id);

    List<SubjectTeacher> findAllBySubject_NameAndDeletedIsFalse(String name);

    List<SubjectTeacher> findAllByTeacher_IdAndDeletedIsFalse(Long id);

    List<SubjectTeacher> findAllByTeacher_CardNumberAndDeletedIsFalse(Long id);

    SubjectTeacher findBySubject_IdAndTeacher_IdAndDeletedIsFalse(Long subjectId, Long teacherId);
}
