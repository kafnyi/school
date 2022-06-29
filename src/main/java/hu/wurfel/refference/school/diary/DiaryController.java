package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/api/v1/Diary/")
public class DiaryController {


	DiaryService diaryService;

	@GetMapping("/search/{searchWith}/{searchBy}/{value}")
	public ResponseEntity<List<Diary>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
		return ResponseEntity.ok(diaryService.getSearchResponseList(searchWith, searchBy, value));
	}

	@PostMapping("/adding")
	public ResponseEntity<List<Diary>> create(@RequestBody DiaryRequestForSearch diaryRequestForSearch) {
		return ResponseEntity.ok(diaryService.save(Integer.parseInt(diaryRequestForSearch.getId()), Long.parseLong(diaryRequestForSearch.getStudentId()), Integer.parseInt(diaryRequestForSearch.getClassId())));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable String id) {
		diaryService.delete(diaryService.getByDiaryId(Integer.parseInt(id)));
    }

	@PutMapping("/modify")
	public ResponseEntity<List<Diary>> modify(@RequestBody DiaryRequestForSearch diaryRequestForSearch) {
		Diary diary = diaryService.getByDiaryId(Integer.parseInt(diaryRequestForSearch.getId()));
		diary.setStudentId(Long.parseLong(diaryRequestForSearch.getStudentId()));
		diary.setClassID(Integer.parseInt(diaryRequestForSearch.getClassId()));
		diaryService.save(diary);
		List<Diary> answer = new ArrayList<>();
		answer.add(diaryService.getByDiaryId(diary.getId()));
		return ResponseEntity.ok(answer);
	}



}
