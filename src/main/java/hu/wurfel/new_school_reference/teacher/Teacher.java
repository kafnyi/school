package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SQLDelete(sql = "UPDATE diary SET deleted = true WHERE id = ?")
public class Teacher extends Auditable {

    @Column(nullable = false, length = 54)
    private String name;

    private Date birthDate;

    @Column(unique = true, length = 10)
    private Long cardNumber;
}
