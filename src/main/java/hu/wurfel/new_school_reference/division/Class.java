package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SQLDelete(sql = "UPDATE diary SET deleted = true WHERE id = ?")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"grade", "sign"}))
public class Class extends Auditable {

    private short grade;

    private char sign;

    public boolean isNotDeleted(){
        return  !this.isDeleted();
    }

}
