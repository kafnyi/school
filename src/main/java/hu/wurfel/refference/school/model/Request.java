package hu.wurfel.refference.school.model;

import lombok.Data;

@Data
public class Request {
	Naming rFor;
	Naming rWith;
	Naming rBy;
	String rValue;
}
