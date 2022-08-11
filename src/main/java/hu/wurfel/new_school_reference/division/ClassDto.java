package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

@Data
public class ClassDto extends BaseDto {

	private short grade;
	private char sign;

	public ClassDto(Class division){
		super(division.getId(),
				division.isDeleted(),
				division.getCreatedAt(),
				division.getUpdatedAt());
		this.grade = division.getGrade();
		this.sign = division.getSign();
	}

	public boolean hasGrade() {
		return grade != 0;
	}

	public boolean hasSign() {
		return sign != 0;
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId()
				&& !hasGrade()
				&& !hasSign();
	}

}
