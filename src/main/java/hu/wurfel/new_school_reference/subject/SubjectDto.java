package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDto extends BaseDto {

	private String title;

	public SubjectDto(Subject subject) {
		super(subject.getId(),
				subject.isDeleted(),
				subject.getCreatedAt(),
				subject.getUpdatedAt()
		);
		this.title = subject.getTitle();
	}

	public boolean hasTitle() {
		return title != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId() && !hasTitle();
	}
}
