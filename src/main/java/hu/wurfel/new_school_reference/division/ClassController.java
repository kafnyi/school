package hu.wurfel.new_school_reference.division;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {

	private final ClassService classService;

	@Autowired
	public ClassController(ClassService classService) {
		this.classService = classService;
	}

	@PostMapping
	public ResponseEntity create(@RequestBody ClassDto classDto) {
		return this.classService.save(classDto);
	}

	@PutMapping
	public ResponseEntity update(@RequestBody ClassDto classDto) {
		return this.classService.save()
	}

	@DeleteMapping
	public ResponseEntity delete(@RequestBody ClassDto classDto) {
		this.classService.deleteById(classDto.getId());
	}
}
