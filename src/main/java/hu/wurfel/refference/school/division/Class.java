package hu.wurfel.refference.school.division;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Year;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "class")
public class Class {
    @Id
    @Column(name = "ClassID", nullable = false)
    private Integer id;

    @Column(name = "Grade", nullable = false)
    private short grade;

    @Column(name = "Sign", nullable = false)
    private char sign;

    @Column(name = "Year", nullable = false)
    private Year year;

    @Column(name = "TID", nullable = false)
    private Long teacherId;

}