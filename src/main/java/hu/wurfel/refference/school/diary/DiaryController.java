package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Diary")
public class DiaryController {


	DiaryService diaryService;

	@GetMapping("/search/{searchWith}/{searchBy}/{value}")
	public ResponseEntity<List<DiaryDto>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
		return ResponseEntity.ok(diaryService.getSearchResponseList(searchWith, searchBy, value));
	}

	@PostMapping
	public ResponseEntity<List<DiaryDto>> create(@RequestBody DiaryDto diaryDto) {
		return ResponseEntity.ok(diaryService.getDtoList(diaryService.save(diaryDto.getId(), diaryDto.getStudentId(), diaryDto.getClassId())));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		diaryService.delete(diaryService.getByDiaryId(Integer.parseInt(id)));
    }

	@PutMapping
	public ResponseEntity<List<DiaryDto>> modify(@RequestBody DiaryDto diaryDto) {
		return ResponseEntity.ok(diaryService.modify(diaryDto));
	}



}
