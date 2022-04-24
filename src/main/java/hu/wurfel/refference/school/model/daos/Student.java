package hu.wurfel.refference.school.model.daos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
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

	@Column(name = "BirthDate", nullable = false)
	private Date birthDate;

	@Override
	public String toString() {
		return "Student{" +
				"ID=" + id +
				", Name='" + name + '\'' +
				", Birthdate=" + birthDate +
				'}';
	}
}