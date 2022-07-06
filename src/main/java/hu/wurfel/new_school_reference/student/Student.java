package hu.wurfel.new_school_reference.student;


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
@Getter
@Setter
public class Student extends Auditable {

    private String name;

    private Date birthDate;

    private long cardNumber;
}
