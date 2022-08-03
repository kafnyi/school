package hu.wurfel.new_school_reference.student;


import hu.wurfel.new_school_reference.base.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<? extends BaseDto> getById(@RequestParam Long id) {
		return ResponseEntity.ok(this.studentService.getById(id));
	}

	@PostMapping
	public ResponseEntity<? extends BaseDto> create(@RequestBody CreateStudentDto dto) {
		return ResponseEntity.ok(this.studentService.save(dto));
	}

	@PutMapping
	public ResponseEntity<? extends BaseDto> update(@RequestBody UpdateStudentDto dto) {
		return ResponseEntity.ok(this.studentService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestParam Long id) {
		this.studentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

//	@DeleteMapping
//	public ResponseEntity<?> delete(@RequestBody StudentDto studentDto) {
//		this.studentService.deleteById(studentDto.getId());
//		return ResponseEntity.noContent().build();
//	}
}
