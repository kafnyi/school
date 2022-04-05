package hu.wurfel.refference.school.controllers;

import hu.wurfel.refference.school.model.StudentSearchDto;
import hu.wurfel.refference.school.model.StudentSearchRequestDto;
import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.services.StudentCrudService;
import hu.wurfel.refference.school.services.StudentSearchTranslate;
import hu.wurfel.refference.school.services.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class StudentSearchController {

	@Autowired
	private StudentCrudService scs;

	@PostMapping("/api/v1/studentSearch")
	public ResponseEntity<Iterable<Student>> findStudent(@RequestBody StudentSearchRequestDto requestDto) {
		System.out.println(requestDto);
		ArrayList<Student> result = new ArrayList<Student>();
		StudentSearchDto searchDto = StudentSearchTranslate.translate(requestDto);
		switch (Validate.StudentSearchDTO(searchDto)) {
			case ID -> result.add(scs.getStudentById(searchDto.getID()));
			case Name -> result.add(scs.getStudentByName(searchDto.getName()));
			case Birth -> result.add(scs.getStudentByBirth(searchDto.getDate()));
			case Diary -> result.add(scs.getStudentByDiary(searchDto.getDiary()));
			case Empty -> result = (ArrayList<Student>) scs.getAllStudent();
		}
		return ResponseEntity.ok(result);

	}

	;
}
