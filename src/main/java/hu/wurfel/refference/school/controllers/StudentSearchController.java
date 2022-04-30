package hu.wurfel.refference.school.controllers;

import hu.wurfel.refference.school.model.StudentRequestDto;
import hu.wurfel.refference.school.model.StudentSearchDto;
import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.model.dtos.StudentDto;
import hu.wurfel.refference.school.services.StudentTranslate;
import hu.wurfel.refference.school.services.Validate;
import hu.wurfel.refference.school.services.cruds.StudentCrudService;
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
	public ResponseEntity<ArrayList<Student>> findStudent(@RequestBody StudentRequestDto requestDto) {
		StudentRequestDto ssrd = requestDto;
		ArrayList<Student> result = new ArrayList<Student>();
		StudentSearchDto searchDto = StudentTranslate.translateToSSDTO(Validate.requestContent(ssrd));
		switch (Validate.searchFor(searchDto)) {
			case ID -> result.add(scs.getStudentByStudentId(searchDto.getID()));
			case Name -> result.add(scs.getStudentByName(searchDto.getName()));
			case Birth -> result.add(scs.getStudentByBirth(searchDto.getDate()));
			case Empty -> result = scs.getAllStudent();
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/api/v1/addNewStudent")
	public void addStudent(@RequestBody StudentDto requestDto) {
		System.out.println(requestDto);
		Student studentToAdd = StudentTranslate.translateToStudent(requestDto);
		scs.addStudent(studentToAdd);
	}

	@PostMapping(value = "/api/v1/deleteStudent")
	public void deleteStudent(@RequestBody StudentRequestDto requestDto) {
		StudentRequestDto srd = requestDto;
		System.out.println(requestDto.toString());
		scs.deleteStudent(scs.getStudentByStudentId(Long.parseLong(srd.getID())));
	}
}
