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

@PostMapping(value = "/api/v1/searchForStudent")
public ResponseEntity<ArrayList<Student>> findStudent(@RequestBody StudentSearchRequestDto requestDto) {
	StudentSearchRequestDto ssrd = requestDto;
	ArrayList<Student> result = new ArrayList<Student>();
	StudentSearchDto searchDto = StudentSearchTranslate.translate(Validate.requestContent(ssrd));
	switch (Validate.searchFor(searchDto)) {
		case ID -> result.add(scs.getStudentById(searchDto.getID()));
		case Name -> result.add(scs.getStudentByName(searchDto.getName()));
		case Birth -> result.add(scs.getStudentByBirth(searchDto.getDate()));
		case Diary -> result.add(scs.getStudentByDiary(searchDto.getDiary()));
		case Empty -> result = scs.getAllStudent();
	}
	return ResponseEntity.ok(result);
	}

	;
}
