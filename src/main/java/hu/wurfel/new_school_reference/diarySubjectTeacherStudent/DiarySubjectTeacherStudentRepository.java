package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiarySubjectTeacherStudentRepository extends JpaRepository<DiarySubjectTeacherStudent, Long> {
    List<DiarySubjectTeacherStudent> findAllByDiary_IdAndDeletedIsFalse(Long id);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_IdAndDeletedIsFalse(Long id);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Teacher_IdAndDeletedIsFalse(Long id);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Teacher_CardNumberAndDeletedIsFalse(Long number);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Subject_IdAndDeletedIsFalse(Long id);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Subject_NameAndDeletedIsFalse(String name);



    List<DiarySubjectTeacherStudent> findAllByStudent_IdAndDeletedIsFalse(Long id);

    List<DiarySubjectTeacherStudent> findAllByStudent_CardNumberAndDeletedIsFalse(Long number);
}
