package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

@Data
public class ClassDto extends BaseDto {

	private Long id;
	private short grade;
	private char sign;

	@Override
	public boolean hasId() {
		return id != null;
	}

	public boolean hasGrade() {
		return grade != 0;
	}

	public boolean hasSign() {
		return sign != 0;
	}

	@Override
	public boolean isEmpty() {
		return !hasId()
				&& !hasGrade()
				&& !hasSign();
	}

}
