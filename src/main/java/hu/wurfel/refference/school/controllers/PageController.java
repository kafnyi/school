package hu.wurfel.refference.school.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
