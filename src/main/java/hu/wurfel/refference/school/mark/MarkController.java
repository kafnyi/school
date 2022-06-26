package hu.wurfel.refference.school.mark;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/v1/Mark")
public class MarkController {


    MarkService markService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody MarkRequestForSearch markRequestForSearch) {
        ArrayList answer = new ArrayList();
        answer = markService.getAutomated(markRequestForSearch);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody MarkRequestForSearch markRequestForSearch) {
        ArrayList answer = new ArrayList();
        answer.add(markService.saveMark(Long.parseLong(markRequestForSearch.getId()), Integer.parseInt(markRequestForSearch.getDiaryId()), markRequestForSearch.getDate(), Integer.parseInt(markRequestForSearch.getSubjectId()), Byte.parseByte(markRequestForSearch.getMark())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        markService.deleteMark(markService.getMarkByMarkid(Long.parseLong(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody MarkRequestForSearch markRequestForSearch) {
        Mark mark = markService.getMarkByMarkid(Long.parseLong(markRequestForSearch.getId()));
        mark.setDiaryID(Integer.parseInt(markRequestForSearch.getDiaryId()));
        mark.setSubjectID(Integer.parseInt(markRequestForSearch.getSubjectId()));
        mark.setDate(markRequestForSearch.getDate());
        mark.setMark(Byte.parseByte(markRequestForSearch.getMark()));
        markService.saveMark(mark);
        ArrayList answer = new ArrayList();
        answer.add(markService.getMarkByMarkid(mark.getId()));
        return ResponseEntity.ok(answer);
    }

}
