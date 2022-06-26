package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import lombok.Data;

import java.io.Serializable;

@Data
public class MarkRequestForSearch implements Serializable {
	private EntityNames searchFor;
	private EntityNames searchWith;
	private EntityFieldNames searchBy;
	private String searchValue;
	private String id;
	private String subjectId;
	private String date;
	private String diaryId;
	private String mark;
}
