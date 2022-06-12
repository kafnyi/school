package hu.wurfel.refference.school.controllers;

import hu.wurfel.refference.school.model.Request;
import hu.wurfel.refference.school.repositories.StudentRepository;
import hu.wurfel.refference.school.services.entity.StudentService;
import hu.wurfel.refference.school.services.entity.cruds.StudentCrudService;
import hu.wurfel.refference.school.services.responseCreators.ResponseCreationClarificationStudent;
import hu.wurfel.refference.school.services.responseCreators.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class PageController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ResponseCreator responseCreator;
	@Autowired
	StudentService studentService;
	@Autowired
	StudentCrudService studentCrudService;
	@Autowired
	ResponseCreationClarificationStudent rccs; //ITTAHIBAAAAAAAA

	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping("/tli")
	public String TLI() {
		return "TLI";
	}

	@RequestMapping("/sli")
	public String SLI() {
		return "SLI";
	}

	@PostMapping("/tli/search/Student")
	public ResponseEntity<ArrayList> searchForStudent(@RequestBody Request request) {
		//System.out.println(request);
		//System.out.println(studentRepository.findAll());
		//System.out.println(studentRepository.findById(Long.parseLong(request.getRValue())));
		ArrayList answer = new ArrayList();
		//answer.add(studentService.getStudentByStudentId(Long.parseLong(request.getRValue())));
		//System.out.println("this is the answer:" + answer);
		answer = responseCreator.create(request);
		System.out.println("the response:" + answer);
		return ResponseEntity.ok(answer);

	}


}
