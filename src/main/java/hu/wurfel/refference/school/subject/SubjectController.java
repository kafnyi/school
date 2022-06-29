package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Subject/")
public class SubjectController {

    SubjectService subjectService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Subject>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(subjectService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping("/adding")
    public ResponseEntity<List<Subject>> create(@RequestBody SubjectRequestForSearch subjectRequestForSearch) {
        return ResponseEntity.ok(subjectService.save(Integer.parseInt(subjectRequestForSearch.getId()), subjectRequestForSearch.getName(), Long.parseLong(subjectRequestForSearch.getTeacherId())));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        subjectService.delete(subjectService.getBySubjectId(Integer.parseInt(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<List<Subject>> modifyStudent(@RequestBody SubjectRequestForSearch subjectRequestForSearch) {
        Subject subject = subjectService.getBySubjectId(Integer.parseInt(subjectRequestForSearch.getId()));
        subject.setSubjectName(subjectRequestForSearch.getName());
        subject.setTid(Long.parseLong(subjectRequestForSearch.getTeacherId()));
        subjectService.save(subject);
        List<Subject> answer = new ArrayList<>();
        answer.add(subjectService.getBySubjectId(subject.getId()));
        return ResponseEntity.ok(answer);
    }



}
