package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;


import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.student.Student;
import hu.wurfel.new_school_reference.subject_teacher.SubjectTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"diary_id","subject_teacher_id", "student_id"}))
public class DiarySubjectTeacherStudent extends Auditable {

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name = "subject_teacher_id")
    private SubjectTeacher subjectTeacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
