package hu.wurfel.new_school_reference.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity create(@RequestBody StudentDto studentDto) {
		return this.studentService.save(studentDto);
	}

	@PutMapping
	public ResponseEntity update(@RequestBody StudentDto studentDto) {
		return this.studentService.save(studentDto);
	}

	@DeleteMapping
	public ResponseEntity delete(@RequestBody StudentDto studentDto) {
		this.studentService.deleteById(studentDto.getId());
		return ResponseEntity.noContent().build();
	}
}
