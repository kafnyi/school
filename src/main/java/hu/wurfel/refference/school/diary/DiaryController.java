package hu.wurfel.refference.school.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DiaryController {


    @Autowired
    DiaryService diaryService;

    @PostMapping("/api/v1/search/Diary")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody DiaryRequest diaryRequest) {
        ArrayList answer = new ArrayList();
        answer = diaryService.getAutomated(diaryRequest);
        return ResponseEntity.ok(answer);
    }

	@PostMapping("/api/v1/adding/Diary")
    public ResponseEntity<ArrayList> adding(@RequestBody DiaryRequest diaryRequest) {
        ArrayList answer = new ArrayList();
        answer.add(diaryService.saveDiary(Integer.parseInt(diaryRequest.getId()), Long.parseLong(diaryRequest.getStudentId()), Integer.parseInt(diaryRequest.getClassId())));
        return ResponseEntity.ok(answer);
    }

	@PostMapping("/api/v1/delete/Diary")
    public void delete(@RequestBody DiaryRequest diaryRequest) {
        diaryService.deleteDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(diaryRequest.getId())));
    }

	@PostMapping("/api/vi/modify/Diary")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody DiaryRequest diaryRequest) {
        Diary diary = diaryService.getDiaryByDiaryid(Integer.parseInt(diaryRequest.getId()));
        diary.setStudentId(Long.parseLong(diaryRequest.getStudentId()));
        diary.setClassID(Integer.parseInt(diaryRequest.getClassId()));
        diaryService.saveDiary(diary);
        ArrayList answer = new ArrayList();
        answer.add(diaryService.getDiaryByDiaryid(diary.getId()));
        return ResponseEntity.ok(answer);
    }

}
