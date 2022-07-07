package hu.wurfel.new_school_reference.student;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDto {

	private Long id;

	private String name;

	private Date birthDate;

	private long cardNumber;

}
