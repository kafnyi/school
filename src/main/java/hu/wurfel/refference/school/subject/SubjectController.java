package hu.wurfel.refference.school.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SubjectController {


    @Autowired
    SubjectService subjectService;

    @PostMapping("/api/v1/search/Subject")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody SubjectRequest subjectRequest) {
        ArrayList answer = new ArrayList();
        answer = subjectService.getAutomated(subjectRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/adding/Subject")
    public ResponseEntity<ArrayList> adding(@RequestBody SubjectRequest subjectRequest) {
        ArrayList answer = new ArrayList();
        answer.add(subjectService.saveSubject(Integer.parseInt(subjectRequest.getId()), subjectRequest.getName(), Long.parseLong(subjectRequest.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/delete/Subject")
    public void delete(@RequestBody SubjectRequest subjectRequest) {
        subjectService.delete(subjectService.getSubjectBySubjectId(Integer.parseInt(subjectRequest.getId())));
    }

    @PostMapping("/api/vi/modify/Subject")
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
