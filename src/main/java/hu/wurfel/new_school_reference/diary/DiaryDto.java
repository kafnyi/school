package hu.wurfel.new_school_reference.diary;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryDto {

	private Long id;
	private Date start;
	private Date end;
	private long divisionId;
	private long headTeacherId;

	public boolean hasId() {
		return id != null;
	}

	public boolean hasStart() {
		return start != null;
	}

	public boolean hasEnd() {
		return end != null;
	}

	public boolean hasDivision() {
		return divisionId != 0;
	}

	public boolean hasHeadTeacher() {
		return headTeacherId != 0;
	}

	//public boolean isEmpty(){
	//return id == null
	//			&& start == null
	//			&& end == null
	//			&& divisionId == 0
	//			&& headTeacherId == 0;
	//}
}
