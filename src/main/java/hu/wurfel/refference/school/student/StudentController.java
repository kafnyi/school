package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.model.AddRequest;
import hu.wurfel.refference.school.model.DeleteRequest;
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

	@Autowired
	StudentService studentService;

	@PostMapping("/api/v1/search/Student")
	public ResponseEntity<ArrayList> searchForStudent(@RequestBody StudentRequest studentRequest) {
		ArrayList answer = new ArrayList();
		answer = responseCreationClarificationStudent.create(studentRequest);
		return ResponseEntity.ok(answer);
	}

	@PostMapping("/api/v1/adding/Student")
	public ResponseEntity<ArrayList> adding (@RequestBody AddRequest addRequest){
		ArrayList answer =new ArrayList();
		answer.add(studentService.saveStudent(Long.parseLong(addRequest.getId()),addRequest.getName(),addRequest.getDate()));
		return  ResponseEntity.ok(answer);
	}

	@PostMapping("/api/v1/delete/Student")
	public void delete(@RequestBody StudentRequest studentRequest){
		studentService.deleteStudent(studentService.getStudentByStudentId(Long.parseLong(studentRequest.getId())));
	}

	@PostMapping("/api/vi/modify/Studet")
	public ResponseEntity<ArrayList> modifyStudent(@RequestBody StudentRequest studentRequest){
		Student student = studentService.getStudentByStudentId(Long.parseLong(studentRequest.getId()));
		student.setName(studentRequest.getName());
		student.setBirthDate(studentRequest.getDate());
		studentService.saveStudent(student);
		ArrayList answer= new ArrayList();
		answer.add(studentService.getStudentByStudentId(student.getId()));
		return ResponseEntity.ok(answer);
	}

}
