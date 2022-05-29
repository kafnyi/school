package hu.wurfel.refference.school.controllers;

import hu.wurfel.refference.school.model.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class PageController {

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
		return null;

	}


}
