package hu.wurfel.new_school_reference.student;

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
public class StudentDto extends BaseDto {

	private Long id;
	private String name;
	private LocalDate birthDate;
	private String cardNumber;

	public StudentDto(Student student) {
		super(student.getId(),
				student.isDeleted(),
				student.getCreatedAt(),
				student.getUpdatedAt());
		this.name = student.getName();
		this.birthDate = student.getBirthDate();
		this.cardNumber = student.getCardNumber();
	}

	public boolean hasName() {
		return name != null;
	}

	public boolean hasBirthDate() {
		return birthDate != null;
	}

	public boolean hasCardNumber() {
		return cardNumber != null && !cardNumber.isBlank();
	}

	@Override
	public boolean isEmpty() {
		return !hasValidId()
				&& !hasName()
				&& !hasBirthDate()
				&& !hasCardNumber();
	}

}
