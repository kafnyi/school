package hu.wurfel.new_school_reference.student;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateStudentDto extends BaseDto {

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
		return !hasValidId()
				&& !hasName()
				&& !hasBirthDate()
				&& !hasCardNumber();
	}

}
