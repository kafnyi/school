package hu.wurfel.new_school_reference.teacher;

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
@Setter
@Getter
@SQLDelete(sql = "UPDATE diary SET deleted = true WHERE id = ?")
public class Teacher extends Auditable {

    @Column(nullable = false, length = 54)
    private String name;

    private LocalDate birthDate;

    @Column(unique = true, length = 10)
    private String cardNumber;

    public boolean isNotDeleted() {
        return !this.isDeleted();
    }
}
