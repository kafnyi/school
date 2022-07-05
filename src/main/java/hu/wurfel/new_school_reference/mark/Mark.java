package hu.wurfel.new_school_reference.mark;


import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diary_subject_teacher_student.Diary_subject_teacher_student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mark extends Auditable {

    private Date testDate;

    @ManyToOne
    @JoinColumn(name = "diary_subject_teacher_student_id")
    private Diary_subject_teacher_student diarySubjectTeacherStudent;

    private short value;

    private MarkModifier markModifier;
}
