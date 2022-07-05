package hu.wurfel.refference.school.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "SID", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false, length = 54)
    private String name;

    @Column(name = "Birthdate", nullable = false)
    private String birthDate;

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Birthdate=" + birthDate +
                '}';
    }
}