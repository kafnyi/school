package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MarkController {


    MarkService markService;

    @GetMapping("/api/v1/Mark/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Mark>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        List<Mark> answer;
        answer = markService.getSearchResponseList(searchWith, searchBy, value);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/Mark/adding")
    public ResponseEntity<List<Mark>> adding(@RequestBody MarkRequestForSearch markRequestForSearch) {
        List<Mark> answer = new ArrayList<>();
        answer.add(markService.save(Long.parseLong(markRequestForSearch.getId()), Integer.parseInt(markRequestForSearch.getDiaryId()), markRequestForSearch.getDate(), Integer.parseInt(markRequestForSearch.getSubjectId()), Byte.parseByte(markRequestForSearch.getMark())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/api/v1/Mark/delete/{id}")
    public void delete(@PathVariable String id) {
        markService.delete(markService.getByMarkid(Long.parseLong(id)));
    }

    @PutMapping("/api/v1/Mark/modify")
    public ResponseEntity<List<Mark>> modifyStudent(@RequestBody MarkRequestForSearch markRequestForSearch) {
        Mark mark = markService.getByMarkid(Long.parseLong(markRequestForSearch.getId()));
        mark.setDiaryID(Integer.parseInt(markRequestForSearch.getDiaryId()));
        mark.setSubjectID(Integer.parseInt(markRequestForSearch.getSubjectId()));
        mark.setDate(markRequestForSearch.getDate());
        mark.setMark(Byte.parseByte(markRequestForSearch.getMark()));
        markService.save(mark);
        List<Mark> answer = new ArrayList<>();
        answer.add(markService.getByMarkid(mark.getId()));
        return ResponseEntity.ok(answer);
    }



}
