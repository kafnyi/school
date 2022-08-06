package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/marks")
public class MarkController {

	private final MarkService markService;

	@Autowired
	public MarkController(MarkService markService) {
		this.markService = markService;
	}

	@PostMapping
	public ResponseEntity<? extends BaseDto> create(@RequestBody CreateMarkDtoWithConnectId dto) {
		return ResponseEntity.ok(this.markService.save(dto));
	}

	public ResponseEntity<? extends BaseDto> createWithDiarySubjectTeacherStudentCreationWithIDs(
			@RequestBody CreateMarkDtoWithDiarySubjectTeacherStudentDtoWithId dto){
		return ResponseEntity.ok(this.markService.saveWithDiarySubjectTeacherStudentSave(dto));
	}

	@PutMapping
	public ResponseEntity<? extends BaseDto> update(@RequestBody UpdateMarkDtoWithConnectId dto) {
		return ResponseEntity.ok(this.markService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@RequestParam Long id) {
		this.markService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
