package hu.wurfel.new_school_reference.teacher;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherDto {

	private Long id;
	private String name;
	private Date birthDate;
	private long cardNumber;

	public boolean hasId() {
		return id != null;
	}

	public boolean hasName() {
		return name != null;
	}

	public boolean hasBirthDate() {
		return birthDate != null;
	}

	public boolean hasCardNumber() {
		return cardNumber != 0;
	}

	public boolean isEmpty() {
		return id == null
				&& name == null
				&& birthDate == null
				&& cardNumber == 0;
	}
}
