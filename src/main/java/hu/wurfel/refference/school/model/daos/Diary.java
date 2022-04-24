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
@Table(name = "diary")
public class Diary {
	@Id
	@Column(name = "DiaryID", nullable = false)
	private Integer id;

	@Column(name = "SCID", nullable = false)
	private Long scid;

	@Column(name = "ClassID", nullable = false)
	private Integer classID;

}