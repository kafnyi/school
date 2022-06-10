package hu.wurfel.refference.school.controllers;

import hu.wurfel.refference.school.model.Request;
import hu.wurfel.refference.school.repositories.StudentRepository;
import hu.wurfel.refference.school.services.responseCreators.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class PageController {
	@Autowired
	StudentRepository studentRepository;


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
	public ArrayList searchForStudent(@RequestBody Request request) {
		System.out.println(request);
		System.out.println(studentRepository.findAll());
		System.out.println(studentRepository.findBySID(Long.parseLong(request.getRValue())));
		return new ResponseCreator().create(request);

	}


}
