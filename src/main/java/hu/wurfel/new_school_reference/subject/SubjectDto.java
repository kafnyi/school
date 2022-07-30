package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

@Data
public class SubjectDto extends BaseDto {

	private Long id;
	private String title;

	@Override
	public boolean hasValidId() {
		return id != null && id>=1;
	}

	public boolean hasName() {
		return title != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId() && !hasName();
	}
}
