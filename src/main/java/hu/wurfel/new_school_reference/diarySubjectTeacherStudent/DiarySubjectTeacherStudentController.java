package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.BaseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diary-subject-teacher-student")
public class DiarySubjectTeacherStudentController {

	private final DiarySubjectTeacherStudentService service;
	private final ModelMapper modelMapper;
	@Autowired
	public DiarySubjectTeacherStudentController(DiarySubjectTeacherStudentService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@PostMapping
	public ResponseEntity<? extends BaseDto> create(@RequestBody CreateDiarySubjectTeacherStudentDto dto) {
		return ResponseEntity.ok(this.service.save(dto));
	}

	@PutMapping
	public ResponseEntity<? extends BaseDto> update(@RequestBody UpdateDiarySubjectTeacherStudentDto dto) {
		return ResponseEntity.ok(this.service.save(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteById(@RequestParam Long id) {
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
