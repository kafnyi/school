package hu.wurfel.refference.school.controllers;

import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.services.StudentCrudService;
import hu.wurfel.refference.school.services.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class StudentSearchController {

	@Autowired
	private StudentCrudService scs;

	@RequestMapping("/login/search")
	public Iterable<Student> findStudent(long ID, String name, Date birth, int diary) {
		ArrayList<Student> result = new ArrayList<Student>();
		switch (Validate.entity(ID, name, birth, diary)) {
			case ID -> result.add(scs.getStudentById(ID));
			case Name -> result.add(scs.getStudentByName(name));
			case Birth -> result.add(scs.getStudentByBirth(birth));
			case Diary -> result.add(scs.getStudentByDiary(diary));
			case Empty -> result = (ArrayList<Student>) scs.getAllStudent();
		}
		return result;

	}

	;
}
