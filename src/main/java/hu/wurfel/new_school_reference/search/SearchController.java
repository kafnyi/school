package hu.wurfel.new_school_reference.search;

import hu.wurfel.new_school_reference.diary.DiaryService;
import hu.wurfel.new_school_reference.division.ClassService;
import hu.wurfel.new_school_reference.mark.MarkService;
import hu.wurfel.new_school_reference.student.StudentService;
import hu.wurfel.new_school_reference.subject.SubjectService;
import hu.wurfel.new_school_reference.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

	private final DiaryService diaryService;
	private final StudentService studentService;
	private final ClassService classService;
	private final TeacherService teacherService;
	private final SubjectService subjectService;
	private final MarkService markService;

	@Autowired
	public SearchController(DiaryService diaryService, StudentService studentService, ClassService classService, TeacherService teacherService, SubjectService subjectService, MarkService markService) {
		this.diaryService = diaryService;
		this.studentService = studentService;
		this.classService = classService;
		this.teacherService = teacherService;
		this.subjectService = subjectService;
		this.markService = markService;
	}

	//region Diary
//
//	@PostMapping("/diary/diary")
//	public ResponseEntity<?> asddiary(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.diaryService.searchBy);
//	}
//
//	@PostMapping("/diary/class")
//	public ResponseEntity<?> asddiary(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.diaryService.searchBy);
//	}
//
//	@PostMapping("/diary/student")
//	public ResponseEntity<?> asddiary(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.diaryService.searchBy);
//	}
//
//	@PostMapping("/diary/teacher")
//	public ResponseEntity<?> asddiary(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.diaryService.searchBy);
//	}
//
//	//endregion Diary
//
//	// region Student
//
//	@PostMapping("/student/student")
//	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.studentService.searchBy);
//	}
//
//	@PostMapping("/student/diary")
//	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.studentService.searchBy);
//	}
//
//	@PostMapping("/student/class")
//	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.studentService.searchBy);
//	}
//
//	@PostMapping("/student/mark")
//	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.studentService.searchBy);
//	}
//
//	@PostMapping("/student/teacher")
//	public ResponseEntity<?> asdstudent(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.studentService.searchBy);
//	}
//
//	// endregion Student
//
//	// region Class
//
//	@PostMapping("/class/class")
//	public ResponseEntity<?> asdclass(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.classService.searchBy);
//	}
//
//	@PostMapping("/class/student")
//	public ResponseEntity<?> asdclass(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.classService.searchBy);
//	}
//
//	@PostMapping("/class/teacher")
//	public ResponseEntity<?> asdclass(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.classService.searchBy);
//	}
//
//	// endregion Class
//
//	// region Teacher
//
//	@PostMapping("/teacher/teacher")
//	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.teacherService.searchBy);
//	}
//
//	@PostMapping("/teacher/student")
//	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.teacherService.searchBy);
//	}
//
//	@PostMapping("/teacher/diary")
//	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.teacherService.searchBy);
//	}
//
//	@PostMapping("/teacher/subject")
//	public ResponseEntity<?> asdteacher(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.teacherService.searchBy);
//	}
//
//	// endregion Teacher
//
//	// region Subject
//
//	@PostMapping("/subject/subject")
//	public ResponseEntity<?> asdsubject(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.subjectService.searchBy);
//	}
//
//	@PostMapping("/subject/student")
//	public ResponseEntity<?> asdsubject(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.subjectService.searchBy);
//	}
//
//	@PostMapping("/subject/teacher")
//	public ResponseEntity<?> asdsubject(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.subjectService.searchBy);
//	}
//
//	// endregion Subject
//
//	//region Mark
//
//	@PostMapping("/mark/mark")
//	public ResponseEntity<?> asdmark(@RequestBody dto dto) {
//		return ResponseEntity.ok(this.markService.searchBy);
//	}
//
//	//endregion Mark
//
}
