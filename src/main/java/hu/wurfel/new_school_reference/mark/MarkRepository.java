package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> findAllByTestDateAndDeletedIsFalse(Date date);

    List<Mark> findAllByDiarySubjectTeacherStudentAndDeletedIsFalse(DiarySubjectTeacherStudent diarySubjectTeacherStudent);

    List<Mark> findAllByValueAndDeletedIsFalse(short value);

    List<Mark> findAllByMarkModifierAndDeletedIsFalse(MarkModifier markModifier);

    List<Mark> findAllByDiarySubjectTeacherStudent_Student_IdAndDeletedIsFalse(Long Id);

    List<Mark> findAllByDiarySubjectTeacherStudent_Diary_IdAndDeletedIsFalse(Long Id);

    List<Mark> findAllByDiarySubjectTeacherStudent_SubjectTeacher_IdAndDeletedIsFalse(Long Id);
}
