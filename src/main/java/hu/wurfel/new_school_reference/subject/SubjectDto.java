package hu.wurfel.new_school_reference.subject;

import lombok.Data;

@Data
public class SubjectDto {

	private Long id;
	private String name;

	public boolean hasId() {
		return id != null;
	}

	public boolean hasName() {
		return name != null;
	}

	public boolean isEmpty() {
		return id == null
				&& name == null;
	}
}
