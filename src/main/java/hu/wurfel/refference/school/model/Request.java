package hu.wurfel.refference.school.model;

import hu.wurfel.refference.school.model.enums.EntityNaming;
import hu.wurfel.refference.school.model.enums.SearchByFields;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Request implements Serializable {
	private final EntityNaming rFor;
	private final EntityNaming rWith;
	private final SearchByFields rBy;
	private final String rValue;
	private final Date rValDate;
}
