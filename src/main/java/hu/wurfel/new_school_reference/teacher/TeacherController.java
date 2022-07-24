package hu.wurfel.new_school_reference.teacher;


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

	@PostMapping
	public ResponseEntity create (@RequestBody TeacherDto teacherDto){
		return this.teacherService.save(teacherDto);
	}

	@PutMapping
	public ResponseEntity update (@RequestBody TeacherDto teacherDto){
		return this.teacherService.save(teacherDto);
	}

	@DeleteMapping
	private void delete (@RequestBody TeacherDto teacherDto){
		this.teacherService.deleteById(teacherDto.getId());
	}
}
