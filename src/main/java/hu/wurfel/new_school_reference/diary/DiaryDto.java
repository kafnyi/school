package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class DiaryDto extends BaseDto {

	private Long id;
	private Date start;
	private Date end;
	private Long divisionId;
	private Long headTeacherId;

	@Override
	public boolean hasValidId() {
		return id != null && id >= 1;
	}

	public boolean hasStart() {
		return start != null;
	}

	public boolean hasEnd() {
		return end != null;
	}

	public boolean hasDivision() {
		return divisionId != null;
	}

	public boolean hasHeadTeacher() {
		return headTeacherId != null;
	}

	@Override
	public boolean isEmpty(){
		return !hasValidId()
				&& !hasStart()
				&& !hasEnd()
				&& !hasDivision()
				&& !hasHeadTeacher();
	}
}
