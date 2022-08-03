package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.BaseDto;
import org.modelmapper.ModelMapper;
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

	@GetMapping("/{id}")
	public ResponseEntity<? extends BaseDto> getById(@RequestParam Long id){
		return ResponseEntity.ok(this.classService.getById(id));
	}

	@PostMapping
	public ResponseEntity<? extends BaseDto> create(@RequestBody CreateClassDto dto) {
		return ResponseEntity.ok(this.classService.save(dto));
	}

	@PutMapping
	public ResponseEntity<? extends BaseDto> update(@RequestBody UpdateClassDto dto) {
		return ResponseEntity.ok(this.classService.update(dto));
	}

	@DeleteMapping
	public ResponseEntity delete(@RequestBody ClassDto classDto) {
		this.classService.deleteById(classDto.getId());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@RequestParam Long id){
		this.classService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
