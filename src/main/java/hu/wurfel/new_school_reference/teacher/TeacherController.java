package hu.wurfel.new_school_reference.teacher;


import hu.wurfel.new_school_reference.base.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

	private final TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<? extends BaseDto> getById(@RequestParam Long id){
		return ResponseEntity.ok(this.teacherService.getById(id));
	}

	@PostMapping
	public ResponseEntity<? extends BaseDto> create (@RequestBody CreateTeacherDto dto){
		return ResponseEntity.ok(this.teacherService.save(dto));
	}

	@PutMapping
	public ResponseEntity<? extends BaseDto> update (@RequestBody UpdateTeacherDto dto){
		return ResponseEntity.ok(this.teacherService.update(dto));
	}

	@DeleteMapping("/{id}")
	private ResponseEntity delete (@RequestParam Long id){
		this.teacherService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
