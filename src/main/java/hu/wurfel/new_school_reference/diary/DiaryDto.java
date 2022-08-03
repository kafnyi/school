package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.BaseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class DiaryDto extends BaseDto {

	private LocalDate start;
	private LocalDate end;
	private Long divisionId;
	private Long headTeacherId;

	public DiaryDto(Diary diary){
		super(diary.getId(),
				diary.isDeleted(),
				diary.getCreatedAt(),
				diary.getUpdatedAt());
		this.start = diary.getStart();
		this.end = diary.getEnd();
		this.divisionId = diary.getDivision().getId();
		this.headTeacherId = diary.getHeadTeacher().getId();
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
