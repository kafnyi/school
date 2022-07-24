package hu.wurfel.new_school_reference.division;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {

	private final ClassService classService;
	private final ModelMapper modelMapper;

	@Autowired
	public ClassController(ClassService classService, ModelMapper modelMapper) {
		this.classService = classService;
		this.modelMapper = modelMapper;
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
