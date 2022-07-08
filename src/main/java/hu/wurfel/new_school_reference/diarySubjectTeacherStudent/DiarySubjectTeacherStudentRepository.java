package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiarySubjectTeacherStudentRepository extends JpaRepository<DiarySubjectTeacherStudent, Long> {
    List<DiarySubjectTeacherStudent> findAllByDiary(Diary diary);

//    List<DiarySubjectTeacherStudent> findAllBySubjectTeacher();
    List<DiarySubjectTeacherStudent> findAllByStudent(Student student);
}
