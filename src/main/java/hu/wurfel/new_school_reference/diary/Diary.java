package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.division.Class;
import hu.wurfel.new_school_reference.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE diary SET deleted = true WHERE id = ?")
public class Diary extends Auditable {

    private Date start;

    private Date end;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Class division;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher headTeacher;
}
