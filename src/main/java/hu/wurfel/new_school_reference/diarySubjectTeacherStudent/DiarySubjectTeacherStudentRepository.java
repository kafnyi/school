package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiarySubjectTeacherStudentRepository extends JpaRepository<DiarySubjectTeacherStudent, Long> {
    List<DiarySubjectTeacherStudent> findAllByDiary_IdAndDeleted(Long id, boolean deleted);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_IdAndDeleted(Long id, boolean deleted);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Teacher_IdAndDeleted(Long id, boolean deleted);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Teacher_CardNumberAndDeleted(Long number, boolean deleted);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Subject_IdAndDeleted(Long id, boolean deleted);

    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher_Subject_NameAndDeleted(String name, boolean deleted);

    List<DiarySubjectTeacherStudent> findAllByStudent_IdAndDeleted(Long id, boolean deleted);

    List<DiarySubjectTeacherStudent> findAllByStudent_CardNumberAndDeleted(Long number, boolean deleted);
}
