package hu.wurfel.refference.school.diary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/v1/Diary")
public class DiaryController {


    DiaryService diaryService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody DiaryRequestForSearch diaryRequestForSearch) {
	    ArrayList answer;
	    answer = diaryService.getAutomated(diaryRequestForSearch);
	    return ResponseEntity.ok(answer);
    }

	@PostMapping("/adding")
	public ResponseEntity<ArrayList> adding(@RequestBody DiaryRequestForSearch diaryRequestForSearch) {
		ArrayList answer = new ArrayList();
		answer.add(diaryService.saveDiary(Integer.parseInt(diaryRequestForSearch.getId()), Long.parseLong(diaryRequestForSearch.getStudentId()), Integer.parseInt(diaryRequestForSearch.getClassId())));
		return ResponseEntity.ok(answer);
	}

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        diaryService.deleteDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(id)));
    }

	@PutMapping("/modify")
	public ResponseEntity<ArrayList> modifyStudent(@RequestBody DiaryRequestForSearch diaryRequestForSearch) {
		Diary diary = diaryService.getDiaryByDiaryid(Integer.parseInt(diaryRequestForSearch.getId()));
		diary.setStudentId(Long.parseLong(diaryRequestForSearch.getStudentId()));
		diary.setClassID(Integer.parseInt(diaryRequestForSearch.getClassId()));
		diaryService.saveDiary(diary);
		ArrayList answer = new ArrayList();
		answer.add(diaryService.getDiaryByDiaryid(diary.getId()));
		return ResponseEntity.ok(answer);
	}

}
