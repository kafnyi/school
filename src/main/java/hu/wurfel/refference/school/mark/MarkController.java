package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Mark")
public class MarkController {


    MarkService markService;

    @GetMapping("/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<MarkDto>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(markService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping
    public ResponseEntity<List<MarkDto>> create(@RequestBody MarkDto markDto) {
        return ResponseEntity.ok(markService.getDtoList(markService.save(markDto.getId(), markDto.getDiaryId(), markDto.getDate(), markDto.getSubjectId(), markDto.getMark())));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        markService.delete(markService.getById(Long.parseLong(id)));
    }

    @PutMapping
    public ResponseEntity<List<MarkDto>> modify(@RequestBody MarkDto markDto) {
        return ResponseEntity.ok(markService.modify(markDto));
    }



}
