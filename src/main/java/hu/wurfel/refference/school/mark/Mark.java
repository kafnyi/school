package hu.wurfel.refference.school.mark;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MarkID", nullable = false)
    private Long id;

    @Column(name = "DiaryID", nullable = false)
    private Integer diaryId;

    @Column(name = "Date", nullable = false)
    private String date;

    @Column(name = "SubjectID", nullable = false)
    private Integer subjectId;

    @Column(name = "Mark", nullable = false)
    private byte mark;

}