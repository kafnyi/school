package hu.wurfel.new_school_reference.subject_teacher;


import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.subject.Subject;
import hu.wurfel.new_school_reference.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE diary SET deleted = true WHERE id = ?")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"subject_id", "teacher_id"}))
public class SubjectTeacher extends Auditable {

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
