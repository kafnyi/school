package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/v1/Subject")
public class SubjectController {

    SubjectService subjectService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}}")
    public ResponseEntity<List<Subject>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        List<Subject> answer;
        answer = getSearchResponseList(searchWith, searchBy, value);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<List<Subject>> adding(@RequestBody SubjectRequestForSearch subjectRequestForSearch) {
        List<Subject> answer = new ArrayList<>();
        answer.add(subjectService.saveSubject(Integer.parseInt(subjectRequestForSearch.getId()), subjectRequestForSearch.getName(), Long.parseLong(subjectRequestForSearch.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        subjectService.delete(subjectService.getSubjectBySubjectId(Integer.parseInt(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<List<Subject>> modifyStudent(@RequestBody SubjectRequestForSearch subjectRequestForSearch) {
        Subject subject = subjectService.getSubjectBySubjectId(Integer.parseInt(subjectRequestForSearch.getId()));
        subject.setSubjectName(subjectRequestForSearch.getName());
        subject.setTid(Long.parseLong(subjectRequestForSearch.getTeacherId()));
        subjectService.saveSubject(subject);
        List<Subject> answer = new ArrayList<>();
        answer.add(subjectService.getSubjectBySubjectId(subject.getId()));
        return ResponseEntity.ok(answer);
    }

    private List<Subject> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return subjectService.sjWStudent(searchBy, value);
            }
            case Diary -> {
                return subjectService.sjWDiary(searchBy, value);
            }
            case Class -> {
                return subjectService.sjWClass(searchBy, value);
            }
            case Subject -> {
                return subjectService.sjWSubject(searchBy, value);
            }
            case Mark -> {
                return subjectService.sjWMark(searchBy, value);
            }
            case Teacher -> {
                return subjectService.sjWTeacher(searchBy, value);
            }
            default -> {
                return null;
            }
        }
    }

}
