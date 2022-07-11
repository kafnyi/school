package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class DiarySearchDto extends BaseDto {

	private Long id;
	private Date start;
	private Long headTeacherId;

	@Override
	public boolean hasId() {
		return id != null;
	}

	public boolean hasStart(){
		return start != null;
	}

	public boolean hasHeadTeacher(){
		return headTeacherId != null;
	}

	@Override
	public boolean isEmpty() {
		return !hasId()
				&& !hasStart()
				&& !hasHeadTeacher();
	}
}
