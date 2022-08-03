package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject-teacher")
public class SubjectTeacherController {

	private final SubjectTeacherService subjectTeacherService;

	@Autowired
	public SubjectTeacherController(SubjectTeacherService subjectTeacherService) {
		this.subjectTeacherService = subjectTeacherService;
	}

	@PostMapping
	public ResponseEntity<? extends BaseDto> create(@RequestBody CreateSubjectTeacherDto dto) {
		return ResponseEntity.ok(this.subjectTeacherService.save(dto));
	}

	@PutMapping
	public ResponseEntity<? extends BaseDto> update(@RequestBody UpdateSubjectTeacherDto dto) {
		return ResponseEntity.ok(this.subjectTeacherService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@RequestParam Long id) {
		this.subjectTeacherService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
