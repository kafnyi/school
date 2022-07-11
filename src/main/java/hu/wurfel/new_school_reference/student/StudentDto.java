package hu.wurfel.new_school_reference.student;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDto extends BaseDto {

	private Long id;
	private String name;
	private Date birthDate;
	private long cardNumber;

	@Override
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

	@Override
	public boolean isEmpty() {
		return !hasId()
				&& !hasName()
				&& !hasBirthDate()
				&& !hasCardNumber();
	}

}
