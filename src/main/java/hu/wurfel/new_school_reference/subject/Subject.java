package hu.wurfel.new_school_reference.subject;


import hu.wurfel.new_school_reference.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE subject SET deleted = true WHERE id = ?")
public class Subject extends Auditable {

    @Column(nullable = false, unique = true)
    private String title;

    public Subject(String title) {
        this.title = title;
    }
}
