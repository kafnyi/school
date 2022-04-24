package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class MarkDto implements Serializable {
	private final Long mId;
	private final Integer dId;
	private final LocalDate date;
	private final Integer sjId;
	private final Integer mark;
}
