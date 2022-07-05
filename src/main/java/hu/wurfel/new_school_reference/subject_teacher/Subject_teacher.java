package hu.wurfel.new_school_reference.subject_teacher;


import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.subject.Subject;
import hu.wurfel.new_school_reference.teacher.Teacher;
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
public class Subject_teacher extends Auditable {

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
