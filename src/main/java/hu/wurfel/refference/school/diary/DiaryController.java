package hu.wurfel.refference.school.diary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/v1/Diary")
public class DiaryController {


    DiaryService diaryService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody DiaryRequest diaryRequest) {
        ArrayList answer;
        answer = diaryService.getAutomated(diaryRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody DiaryRequest diaryRequest) {
        ArrayList answer = new ArrayList();
        answer.add(diaryService.saveDiary(Integer.parseInt(diaryRequest.getId()), Long.parseLong(diaryRequest.getStudentId()), Integer.parseInt(diaryRequest.getClassId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        diaryService.deleteDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(id)));
    }

    @PutMapping("/modify")
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
