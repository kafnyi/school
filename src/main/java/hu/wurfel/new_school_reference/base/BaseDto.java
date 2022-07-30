package hu.wurfel.new_school_reference.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto implements Dto {

	private Long id;
	private boolean deleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public boolean hasValidId() {
		return id != null && id >= 1;
	}

}
