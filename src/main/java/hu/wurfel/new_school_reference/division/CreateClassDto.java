package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.Dto;
import lombok.Data;

@Data
public class CreateClassDto implements Dto {

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
		return !hasGrade()
				&& !hasSign();
	}
}
