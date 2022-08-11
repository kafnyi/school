package hu.wurfel.new_school_reference.mark;


import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE diary SET deleted = true WHERE id = ?")
public class Mark extends Auditable {

    private LocalDate testDate;

    @ManyToOne
    @JoinColumn(name = "diary_subject_teacher_student_id")
    private DiarySubjectTeacherStudent diarySubjectTeacherStudent;

    @Column(length = 1)
    private short value;

    private MarkModifier markModifier;
}
