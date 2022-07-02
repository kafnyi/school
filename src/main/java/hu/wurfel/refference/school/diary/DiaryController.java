package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Diary")
public class DiaryController {


	DiaryService diaryService;

	@GetMapping("/search/{searchWith}/{searchBy}/{value}")
	public ResponseEntity<List<Diary>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
		return ResponseEntity.ok(diaryService.getSearchResponseList(searchWith, searchBy, value));
	}

	@PostMapping
	public ResponseEntity<List<Diary>> create(@RequestBody DiaryDto diaryDto) {
		return ResponseEntity.ok(diaryService.save(diaryDto.getId(), diaryDto.getStudentId(), diaryDto.getClassId()));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		diaryService.delete(diaryService.getByDiaryId(Integer.parseInt(id)));
    }

	@PutMapping
	public ResponseEntity<List<Diary>> modify(@RequestBody DiaryDto diaryDto) {
		Diary diary = diaryService.getByDiaryId(diaryDto.getId());
		diary.setStudentId(diaryDto.getStudentId());
		diary.setClassID(diary.getClassID());
		diaryService.save(diary);
		List<Diary> answer = new ArrayList<>();
		answer.add(diaryService.getByDiaryId(diary.getId()));
		return ResponseEntity.ok(answer);
	}



}
