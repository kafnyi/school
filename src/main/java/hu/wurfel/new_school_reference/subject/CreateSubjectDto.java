package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.Dto;
import lombok.Data;

@Data
public class CreateSubjectDto implements Dto {

	private String title;

	public boolean hasTitle() {
		return title != null
				&& !title.isBlank();
	}

	@Override
	public boolean isEmpty() {
		return !hasTitle();
	}

}
