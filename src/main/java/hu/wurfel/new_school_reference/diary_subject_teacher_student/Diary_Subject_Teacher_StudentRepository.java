package hu.wurfel.new_school_reference.diary_subject_teacher_student;

import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.student.Student;
import hu.wurfel.new_school_reference.subject_teacher.Subject_teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Diary_Subject_Teacher_StudentRepository extends JpaRepository<Diary_subject_teacher_student, Long> {
    List<Diary_subject_teacher_student> findAllByDiary(Diary diary);

    List<Diary_subject_teacher_student> findAllBySubject_teacher(Subject_teacher subject_teacher);

    List<Diary_subject_teacher_student> findAllByStudent(Student student);
}
