package hu.wurfel.new_school_reference.student;

import hu.wurfel.new_school_reference.base.Dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStudentDto implements Dto {

	private String name;
	private LocalDate birthDate;
	private String cardNumber;

	public boolean hasName() {
		return name != null;
	}

	public boolean hasBirthDate() {
		return birthDate != null;
	}

	public boolean hasCardNumber() {
		return cardNumber != null && !cardNumber.isBlank();
	}

	@Override
	public boolean isEmpty() {
		return !hasName()
				&& !hasBirthDate()
				&& !hasCardNumber();
	}

}
