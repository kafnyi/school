package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> findAllByTestDateAndDeleted(Date date, boolean deleted);

    List<Mark> findAllByDiarySubjectTeacherStudent_IdAndDeleted(Long id, boolean deleted);

    List<Mark> findAllByValueAndDeleted(short value, boolean deleted);

    List<Mark> findAllByMarkModifierAndDeleted(MarkModifier markModifier, boolean deleted);

    List<Mark> findAllByDiarySubjectTeacherStudent_Student_IdAndDeleted(Long Id, boolean deleted);

    List<Mark> findAllByDiarySubjectTeacherStudent_Diary_IdAndDeleted(Long Id, boolean deleted);

    List<Mark> findAllByDiarySubjectTeacherStudent_SubjectTeacher_IdAndDeleted(Long Id, boolean deleted);
}
