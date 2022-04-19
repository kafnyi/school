package hu.wurfel.refference.school.services;


import hu.wurfel.refference.school.model.StudentRequestDto;
import hu.wurfel.refference.school.model.StudentSearchDto;
import hu.wurfel.refference.school.model.daos.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentTranslate {


	public static StudentSearchDto translateToSSDTO(StudentRequestDto requestDto) {
		return new StudentSearchDto(Long.parseLong(requestDto.getID()), requestDto.getName(), requestDto.getBirth(), Integer.parseInt(requestDto.getDiary()));
	}

	public static Student translateToStudent(StudentRequestDto requestDto) {
		return new Student(Long.parseLong(requestDto.getID()), requestDto.getName(), requestDto.getBirth(), Integer.parseInt(requestDto.getDiary()));
	}

}
