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
@Table(name = "class")
public class Class {
	@Id
	@Column(name = "ClassID", nullable = false)
	private Integer id;

	@Column(name = "Grade", nullable = false)
	private Integer grade;

	@Column(name = "Sign", nullable = false)
	private Character sign;

	@Column(name = "Year", nullable = false)
	private Integer year;

	@Column(name = "TID", nullable = false)
	private Long tid;

}