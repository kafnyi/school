package hu.wurfel.refference.school.model;

import hu.wurfel.refference.school.model.enums.EntityNaming;
import hu.wurfel.refference.school.model.enums.SearchByFields;
import lombok.Data;

import java.util.Date;

@Data
public class Request {
	EntityNaming rFor;
	EntityNaming rWith;
	SearchByFields rBy;
	String rValue;
	Date rValDate;
}
