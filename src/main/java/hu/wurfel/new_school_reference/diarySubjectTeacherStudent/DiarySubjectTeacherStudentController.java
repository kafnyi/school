package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diary_subject_teacher_student")
public class DiarySubjectTeacherStudentController {

	private final DiarySubjectTeacherStudentService service;
	private final ModelMapper modelMapper;
	@Autowired
	public DiarySubjectTeacherStudentController(DiarySubjectTeacherStudentService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@PostMapping
	public ResponseEntity create(@RequestBody DiarySubjectTeacherStudentDto dto) {
		return this.service.save(dto);
	}

	@PutMapping
	public ResponseEntity update(@RequestBody DiarySubjectTeacherStudentDto dto) {
		return this.service.save(dto);
	}

	@DeleteMapping
	public void delete(@RequestBody DiarySubjectTeacherStudentDto dto) {
		this.service.deleteById(dto.getId());
	}
}
