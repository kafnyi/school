package hu.wurfel.new_school_reference.diary;

import lombok.Data;

import java.util.Date;

@Data
public class DiarySearchDto {

	private long id;
	private Date start;
	private Date end;
	private long classId;
	private long headTeacherId;

}
