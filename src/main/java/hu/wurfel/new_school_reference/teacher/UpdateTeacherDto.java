package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTeacherDto extends BaseDto {

	private String name;
	private LocalDate birthDate;
	private String cardNumber;

	public boolean hasName(){
		return name != null
				&& !name.isBlank();
	}

	public boolean hasBirthDate(){
		return birthDate != null;
	}

	public boolean hasCardNumber(){
		return cardNumber != null
				&& !cardNumber.isBlank();
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId()
				&& !hasName()
				&& hasBirthDate()
				&& hasCardNumber();
	}

}
