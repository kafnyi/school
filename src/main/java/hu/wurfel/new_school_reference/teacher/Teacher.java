package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Teacher extends Auditable {

    private String name;

    private Date birthDate;

    private Long cardNumber;
}
