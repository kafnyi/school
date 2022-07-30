package hu.wurfel.new_school_reference.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto {

	private boolean deleted;

	public abstract boolean hasValidId();

	public abstract boolean isEmpty();
}
