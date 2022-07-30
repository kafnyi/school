package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherDto extends BaseDto {

	private Long id;
	private String name;
	private Date birthDate;
	private long cardNumber;

	@Override
	public boolean hasValidId() {
		return id != null && id >= 1;
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
		return !hasValidId()
				&& !hasName()
				&& !hasBirthDate()
				&& !hasCardNumber();
	}
}
