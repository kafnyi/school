package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSubjectDto extends BaseDto {

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
