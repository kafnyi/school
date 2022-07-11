package hu.wurfel.new_school_reference.search;

import hu.wurfel.new_school_reference.diary.DiaryDto;
import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

	private final SearchService searchService;

	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}


	//region Diary

	@PostMapping("/diary/diary")
	public ResponseEntity<?> search(@RequestBody DiaryDto dto) {
		return ResponseEntity.ok(searchService.findDiaryByDiary(dto));
	}

	@PostMapping("/diary/class")
	public ResponseEntity<?> asddiary(@RequestBody ClassDto dto) {
		return ResponseEntity.ok(this.diaryService.findAllByClass(dto));
	}

	@PostMapping("/diary/student")
	public ResponseEntity<?> asddiary(@RequestBody TeacherDto dto) {
		return ResponseEntity.ok(this.diaryService.find);
	}

	@PostMapping("/diary/teacher")
	public ResponseEntity<?> asddiary(@RequestBody dto dto) {
		return ResponseEntity.ok(this.diaryService.searchBy);
	}

	//endregion Diary

	// region Student

	@PostMapping("/student/student")
	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
		return ResponseEntity.ok(this.studentService.searchBy);
	}

	@PostMapping("/student/diary")
	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
		return ResponseEntity.ok(this.studentService.searchBy);
	}

	@PostMapping("/student/class")
	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
		return ResponseEntity.ok(this.studentService.searchBy);
	}

	@PostMapping("/student/mark")
	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
		return ResponseEntity.ok(this.studentService.searchBy);
	}

	@PostMapping("/student/teacher")
	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
		return ResponseEntity.ok(this.studentService.searchBy);
	}

	// endregion Student

	// region Class

	@PostMapping("/class/class")
	public ResponseEntity<?> asdclass(@RequestBody dto dto) {
		return ResponseEntity.ok(this.classService.searchBy);
	}

	@PostMapping("/class/student")
	public ResponseEntity<?> asdclass(@RequestBody dto dto) {
		return ResponseEntity.ok(this.classService.searchBy);
	}

	@PostMapping("/class/teacher")
	public ResponseEntity<?> asdclass(@RequestBody dto dto) {
		return ResponseEntity.ok(this.classService.searchBy);
	}

	// endregion Class

	// region Teacher

	@PostMapping("/teacher/teacher")
	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
		return ResponseEntity.ok(this.teacherService.searchBy);
	}

	@PostMapping("/teacher/student")
	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
		return ResponseEntity.ok(this.teacherService.searchBy);
	}

	@PostMapping("/teacher/diary")
	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
		return ResponseEntity.ok(this.teacherService.searchBy);
	}

	@PostMapping("/teacher/subject")
	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
		return ResponseEntity.ok(this.teacherService.searchBy);
	}

	// endregion Teacher

	// region Subject

	@PostMapping("/subject/subject")
	public ResponseEntity<?> asdsubject(@RequestBody dto dto) {
		return ResponseEntity.ok(this.subjectService.searchBy);
	}

	@PostMapping("/subject/student")
	public ResponseEntity<?> asdsubject(@RequestBody dto dto) {
		return ResponseEntity.ok(this.subjectService.searchBy);
	}

	@PostMapping("/subject/teacher")
	public ResponseEntity<?> asdsubject(@RequestBody dto dto) {
		return ResponseEntity.ok(this.subjectService.searchBy);
	}

	// endregion Subject

	//region Mark

	@PostMapping("/mark/mark")
	public ResponseEntity<?> asdmark(@RequestBody dto dto) {
		return ResponseEntity.ok(this.markService.searchBy);
	}

	//endregion Mark

}
