package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Subject")
public class SubjectController {

    SubjectService subjectService;

    @GetMapping("/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<SubjectDto>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(subjectService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping
    public ResponseEntity<List<SubjectDto>> create(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.getDtoList(subjectService.save(subjectDto.getId(), subjectDto.getName(), subjectDto.getTeacherId())));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        subjectService.delete(subjectService.getBySubjectId(Integer.parseInt(id)));
    }

    @PutMapping
    public ResponseEntity<List<SubjectDto>> modifyStudent(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.modify(subjectDto));
    }



}
