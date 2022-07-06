package hu.wurfel.new_school_reference.diary_subject_teacher_student;

import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiarySubjectTeacherStudentRepository extends JpaRepository<Diary_Subject_Teacher_Student, Long> {
    List<Diary_Subject_Teacher_Student> findAllByDiary(Diary diary);
    List<Diary_Subject_Teacher_Student> findAllByStudent(Student student);
}
