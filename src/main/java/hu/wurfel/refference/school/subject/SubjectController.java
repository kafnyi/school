package hu.wurfel.refference.school.subject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/v1/Subject")
public class SubjectController {

    SubjectService subjectService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody SubjectRequestForSearch subjectRequestForSearch) {
        ArrayList answer = new ArrayList();
        answer = subjectService.getAutomated(subjectRequestForSearch);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody SubjectRequestForSearch subjectRequestForSearch) {
        ArrayList answer = new ArrayList();
        answer.add(subjectService.saveSubject(Integer.parseInt(subjectRequestForSearch.getId()), subjectRequestForSearch.getName(), Long.parseLong(subjectRequestForSearch.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        subjectService.delete(subjectService.getSubjectBySubjectId(Integer.parseInt(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody SubjectRequestForSearch subjectRequestForSearch) {
        Subject subject = subjectService.getSubjectBySubjectId(Integer.parseInt(subjectRequestForSearch.getId()));
        subject.setSubjectName(subjectRequestForSearch.getName());
        subject.setTid(Long.parseLong(subjectRequestForSearch.getTeacherId()));
        subjectService.saveSubject(subject);
        ArrayList answer = new ArrayList();
        answer.add(subjectService.getSubjectBySubjectId(subject.getId()));
        return ResponseEntity.ok(answer);
    }

}
