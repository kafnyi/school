package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto extends BaseDto {

	private Long id;
	private String name;
	private LocalDate birthDate;
	private String cardNumber;

	public TeacherDto(Teacher teacher) {
		super(teacher.getId(),
				teacher.isDeleted(),
				teacher.getCreatedAt(),
				teacher.getUpdatedAt());
		this.name = teacher.getName();
		this.birthDate = teacher.getBirthDate();
		this.cardNumber = teacher.getCardNumber();
	}


	public boolean hasName() {
		return name != null;
	}

	public boolean hasBirthDate() {
		return birthDate != null;
	}

	public boolean hasCardNumber() {
		return cardNumber != null
				&& !cardNumber.isBlank();
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId()
				&& !hasName()
				&& !hasBirthDate()
				&& !hasCardNumber();
	}

}
