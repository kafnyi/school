package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Mark")
public class MarkController {


    MarkService markService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Mark>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(markService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping
    public ResponseEntity<List<Mark>> create(@RequestBody MarkDto markDto) {
        return ResponseEntity.ok(markService.save(markDto.getId(), markDto.getDiaryId(), markDto.getDate(), markDto.getSubjectId(), markDto.getMark()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        markService.delete(markService.getByMarkId(Long.parseLong(id)));
    }

    @PutMapping
    public ResponseEntity<List<Mark>> modifyStudent(@RequestBody MarkRequestForSearch markRequestForSearch) {
	    Mark mark = markService.getByMarkId(Long.parseLong(markRequestForSearch.getId()));
        mark.setDiaryID(Integer.parseInt(markRequestForSearch.getDiaryId()));
        mark.setSubjectID(Integer.parseInt(markRequestForSearch.getSubjectId()));
        mark.setDate(markRequestForSearch.getDate());
        mark.setMark(Byte.parseByte(markRequestForSearch.getMark()));
        markService.save(mark);
        List<Mark> answer = new ArrayList<>();
	    answer.add(markService.getByMarkId(mark.getId()));
        return ResponseEntity.ok(answer);
    }



}
