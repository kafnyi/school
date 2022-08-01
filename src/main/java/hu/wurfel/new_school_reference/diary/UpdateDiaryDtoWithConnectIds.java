package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.Dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDiaryDtoWithConnectIds extends BaseDto {

	private LocalDate start;
	private LocalDate end;
	private Long divisionId;
	private Long headTeacherId;

	public boolean hasStart(){
		return start != null;
	}

	public boolean hasEnd(){
		return end != null;
	}

	public boolean hasValidDivisionId(){
		return divisionId != null
				&& divisionId > 0;
	}

	public boolean hasValidHeadTeacherId(){
		return headTeacherId != null
				&& headTeacherId > 0;
	}

	@Override
	public boolean isEmpty() {
		return !hasStart()
				&& !hasEnd()
				&& !hasValidDivisionId()
				&& !hasValidHeadTeacherId();
	}
}
