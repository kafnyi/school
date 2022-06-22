package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.model.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class StudentController {

	@Autowired
	ResponseCreationClarificationStudent responseCreationClarificationStudent;


	@PostMapping("/api/v1/search/Student")
	public ResponseEntity<ArrayList> searchForStudent(@RequestBody SearchRequest request) {
		ArrayList answer = responseCreationClarificationStudent.create(request);
		return ResponseEntity.ok(answer);
	}


}
