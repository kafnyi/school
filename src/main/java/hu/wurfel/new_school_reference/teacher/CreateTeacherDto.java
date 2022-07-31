package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.Dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTeacherDto implements Dto {

	private String name;
	private LocalDate birthDate;
	private String cardNumber;

	public boolean hasName() {
		return name != null
				&& !name.isBlank();
	}

	public boolean hasBirthDate() {
		return birthDate != null;
	}

	public boolean hasCardNumber() {
		return cardNumber != null
				&& !cardNumber.isBlank();
	}

	@Override
	public boolean isEmpty() {
		return !hasName()
				&& !hasBirthDate()
				&& !hasCardNumber();
	}
}
