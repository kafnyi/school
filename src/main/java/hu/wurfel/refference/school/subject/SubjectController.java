package hu.wurfel.refference.school.subject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/v1/Subject")
public class SubjectController {

    SubjectService subjectService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody SubjectRequest subjectRequest) {
        ArrayList answer = new ArrayList();
        answer = subjectService.getAutomated(subjectRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody SubjectRequest subjectRequest) {
        ArrayList answer = new ArrayList();
        answer.add(subjectService.saveSubject(Integer.parseInt(subjectRequest.getId()), subjectRequest.getName(), Long.parseLong(subjectRequest.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        subjectService.delete(subjectService.getSubjectBySubjectId(Integer.parseInt(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody SubjectRequest subjectRequest) {
        Subject subject = subjectService.getSubjectBySubjectId(Integer.parseInt(subjectRequest.getId()));
        subject.setSubjectName(subjectRequest.getName());
        subject.setTid(Long.parseLong(subjectRequest.getTeacherId()));
        subjectService.saveSubject(subject);
        ArrayList answer = new ArrayList();
        answer.add(subjectService.getSubjectBySubjectId(subject.getId()));
        return ResponseEntity.ok(answer);
    }

}
