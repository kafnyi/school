package hu.wurfel.refference.school.model.daos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "SubjectID", nullable = false)
    private Integer id;

    @Column(name = "SubjectName", nullable = false, length = 32)
    private String subjectName;

    @Column(name = "TID", nullable = false)
    private Long tid;

}