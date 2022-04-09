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

//	@RequestMapping(value = "/login/searchForStudent", method = RequestMethod.POST,
//	                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//	                produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@PostMapping(value = "/login/searchForStudent", produces = "application/json", consumes = "application/json")
public ResponseEntity<Iterable<Student>> findStudent(@RequestBody StudentSearchRequestDto requestDto) {
	System.out.println(requestDto);
	ArrayList<Student> result = new ArrayList<Student>();
	StudentSearchDto searchDto = StudentSearchTranslate.translate(Validate.requestContent(requestDto));
	switch (Validate.searchFor(searchDto)) {
		case ID -> result.add(scs.getStudentById(searchDto.getID()));
		case Name -> result.add(scs.getStudentByName(searchDto.getName()));
		case Birth -> result.add(scs.getStudentByBirth(searchDto.getDate()));
		case Diary -> result.add(scs.getStudentByDiary(searchDto.getDiary()));
		case Empty -> result = scs.getAllStudent();
	}
	System.out.println(result);
	System.out.println("response sending");
	return ResponseEntity.ok(result);

	}

	;
}
