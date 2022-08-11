package hu.wurfel.new_school_reference.student;


import hu.wurfel.new_school_reference.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE student SET deleted = true WHERE id = ?")
public class Student extends Auditable {

    @Column(nullable = false , length = 54)
    private String name;

    private LocalDate birthDate;

    @Column(unique = true, length = 11)
    private String cardNumber;

    public boolean isNotDeleted() {
        return !this.isDeleted();
    }
}
