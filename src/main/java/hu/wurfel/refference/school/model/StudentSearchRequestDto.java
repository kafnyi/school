package hu.wurfel.refference.school.model;

import lombok.Data;

@Data
public class StudentSearchRequestDto {
	private String ID;
	private String Name;
	private String Date;
	private String Diary;
}