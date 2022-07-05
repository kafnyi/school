package hu.wurfel.new_school_reference.diary_subject_teacher_student;


import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.student.Student;
import hu.wurfel.new_school_reference.subject_teacher.Subject_teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Diary_subject_teacher_student extends Auditable {

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name = "subject_teacher_id")
    private Subject_teacher subject_teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
