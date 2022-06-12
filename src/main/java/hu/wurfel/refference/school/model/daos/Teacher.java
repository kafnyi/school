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
@Entity
@Table(name = "teacher")
public class Teacher {
	@Id
	@Column(name = "TID", nullable = false)
	private Long id;

	@Column(name = "Name", nullable = false, length = 54)
	private String name;

	@Column(name = "BirthDate", nullable = false)
	private String birthDate;

	@Override
	public String toString() {
		return "Teacher{" +
				"ID=" + id +
				", Name='" + name + '\'' +
				", Birthdate=" + birthDate +
				'}';
	}
}