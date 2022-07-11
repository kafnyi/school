package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> findAllByTestDate(Date date);

    List<Mark> findAllByDiarySubjectTeacherStudent(DiarySubjectTeacherStudent diarySubjectTeacherStudent);

    List<Mark> findAllByValue(short value);

    List<Mark> findAllByMarkModifier(MarkModifier markModifier);
}
