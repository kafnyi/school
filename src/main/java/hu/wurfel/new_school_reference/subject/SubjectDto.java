package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

@Data
public class SubjectDto extends BaseDto {

	private Long id;
	private String name;

	@Override
	public boolean hasId() {
		return id != null;
	}

	public boolean hasName() {
		return name != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasId() && !hasName();
	}
}
