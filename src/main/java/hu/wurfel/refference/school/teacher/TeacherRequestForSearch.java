package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import lombok.Data;

@Data
public class TeacherRequestForSearch {
	private EntityNames searchFor;
	private EntityNames searchWith;
	private EntityFieldNames searchBy;
	private String searchValue;
	private String id;
	private String name;
	private String date;
}
