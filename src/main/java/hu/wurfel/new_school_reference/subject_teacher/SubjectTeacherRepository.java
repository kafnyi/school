package hu.wurfel.new_school_reference.subject_teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {
    List<SubjectTeacher> findAllBySubject_IdAndDeleted(Long id, boolean deleted);

    List<SubjectTeacher> findAllBySubject_NameAndDeleted(String name, boolean deleted);

    List<SubjectTeacher> findAllByTeacher_IdAndDeleted(Long id, boolean deleted);

    List<SubjectTeacher> findAllByTeacher_CardNumberAndDeleted(Long id, boolean deleted);

    SubjectTeacher findBySubject_IdAndTeacher_IdAndDeleted(Long subjectId, Long teacherId, boolean deleted);
}
