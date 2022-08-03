package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.Dto;
import hu.wurfel.new_school_reference.division.CreateClassDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateDiaryDtoWithCreateClassDto implements Dto {

	private LocalDate start;
	private LocalDate end;
	private CreateClassDto createClassDto;
	private Long headTeacherId;

	public boolean hasStart(){
		return start != null;
	}

	public boolean hasEnd(){
		return end != null;
	}

	public boolean hasValidDivisionToCreate(){
		return createClassDto != null
				&& createClassDto.hasSign()
				&& createClassDto.hasGrade();
	}

	public boolean hasValidHeadTeacherId(){
		return headTeacherId != null
				&& headTeacherId > 0;
	}

	@Override
	public boolean isEmpty() {
		return !hasStart()
				&& !hasEnd()
				&& !hasValidDivisionToCreate()
				&& !hasValidHeadTeacherId();
	}
}
