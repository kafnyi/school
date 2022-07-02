package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import lombok.Data;

@Data
public class ClassRequestForSearch {
	private EntityNames searchFor;
	private EntityNames searchWith;
	private EntityFieldNames searchBy;
	private String searchValue;
	private String id;
	private String grade;
	private String sign;
	private String year;
	private String teacherId;
}
