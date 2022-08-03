package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

	private final SubjectService subjectService;

	@Autowired
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<? extends BaseDto> getById(@RequestParam Long id) {
		return ResponseEntity.ok(this.subjectService.getById(id));
	}

	@PostMapping
	public ResponseEntity<? extends BaseDto> create(@RequestBody CreateSubjectDto dto) {
		return ResponseEntity.ok(this.subjectService.save(dto));
	}

	@PutMapping
	public ResponseEntity<? extends BaseDto> update(@RequestBody UpdateSubjectDto dto) {
		return ResponseEntity.ok(this.subjectService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestParam Long id) {
		this.subjectService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

//	@DeleteMapping
//	public ResponseEntity<?> delete(@RequestBody SubjectDto subjectDto) {
//		this.subjectService.deleteById(subjectDto.getId());
//		return ResponseEntity.noContent().build();
//	}

}
