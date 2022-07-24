package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diary_subject_teacher_student")
public class DiarySubjectTeacherStudentController {

	private final DiarySubjectTeacherStudentService diarySubjectTeacherStudentService;

	@Autowired
	public DiarySubjectTeacherStudentController(DiarySubjectTeacherStudentService diarySubjectTeacherStudentService) {
		this.diarySubjectTeacherStudentService = diarySubjectTeacherStudentService;
	}

	@PostMapping
	public ResponseEntity create(@RequestMapping DiarySubjectTeacherStudentDto dto) {
		return this.diarySubjectTeacherStudentService.save(dto);
	}

	@PutMapping
	public ResponseEntity update(@RequestMapping DiarySubjectTeacherStudentDto dto) {
		return this.diarySubjectTeacherStudentService.save(dto);
	}

	@DeleteMapping
	public void delete(@RequestMapping DiarySubjectTeacherStudentDto dto) {
		this.diarySubjectTeacherStudentService.deleteById(dto.getId());
	}
}
