package hu.wurfel.new_school_reference.division;

import lombok.Data;

@Data
public class ClassDto {

	private Long id;
	private short grade;
	private char sign;

	public boolean hasId() {
		return id != null;
	}

	public boolean hasGrade() {
		return grade != 0;
	}

	public boolean hasSign() {
		return sign != 0;
	}

	public boolean isEmpty() {
		return !hasId()
				&& !hasGrade()
				&& !hasSign();
	}

}
