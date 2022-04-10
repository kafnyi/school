package hu.wurfel.refference.school.services;


import hu.wurfel.refference.school.model.StudentSearchDto;
import hu.wurfel.refference.school.model.StudentSearchRequestDto;
import org.springframework.stereotype.Service;

@Service
public class StudentSearchTranslate {


	public static StudentSearchDto translate(StudentSearchRequestDto requestDto) {
		return new StudentSearchDto(Long.parseLong(requestDto.getID()), requestDto.getName(), requestDto.getBirth(), Integer.parseInt(requestDto.getDiary()));
	}

	;

}
