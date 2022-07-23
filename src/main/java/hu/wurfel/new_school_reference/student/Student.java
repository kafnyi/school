package hu.wurfel.new_school_reference.student;


import hu.wurfel.new_school_reference.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE diary SET deleted = true WHERE id = ?")
public class Student extends Auditable {

    @Column(nullable = false , length = 54)
    private String name;

    private Date birthDate;

    @Column(unique = true , length = 11)
    private long cardNumber;
}
