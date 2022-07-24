package hu.wurfel.new_school_reference.subject_teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject_teacher")
public class SubjectTeacherController {

	private final SubjectTeacherService subjectTeacherService;

	@Autowired
	public SubjectTeacherController(SubjectTeacherService subjectTeacherService) {
		this.subjectTeacherService = subjectTeacherService;
	}

	@PostMapping
	public ResponseEntity create(@RequestBody SubjectTeacherDto subjectTeacherDto) {
		return this.subjectTeacherService.save(subjectTeacherDto);
	}

	@PutMapping
	public ResponseEntity update(@RequestBody SubjectTeacherDto subjectTeacherDto) {
		return this.subjectTeacherService.save(subjectTeacherDto);
	}

	@DeleteMapping
	public void delete(@RequestBody SubjectTeacherDto subjectTeacherDto) {
		this.subjectTeacherService.deleteById(subjectTeacherDto.getId());
	}
}
