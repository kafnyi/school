package hu.wurfel.new_school_reference.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

	private final SubjectService subjectService;

	@Autowired
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@PostMapping
	public ResponseEntity create(@RequestBody SubjectDto subjectDto) {
		return this.subjectService.save(subjectDto);
	}

	@PutMapping
	public ResponseEntity update(@RequestBody SubjectDto subjectDto) {
		return this.subjectService.save(subjectDto);
	}

	@DeleteMapping
	public void delete(@RequestBody SubjectDto subjectDto) {
		this.subjectService.deleteById(subjectDto.getId());
	}

}
