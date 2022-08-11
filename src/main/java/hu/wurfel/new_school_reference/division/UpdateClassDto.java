package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

@Data
public class UpdateClassDto extends BaseDto {

	private short grade;
	private char sign;

	public boolean hasGrade(){
		return grade != 0;
	}

	public boolean hasSign(){
		return sign != 0;
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId()
				&& !hasGrade()
				&& !hasSign();
	}

}
