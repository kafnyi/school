package hu.wurfel.refference.school.mark;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MarkController {


    MarkService markService;

    @PostMapping("/api/v1/search/Mark")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody MarkRequest markRequest) {
        ArrayList answer = new ArrayList();
        answer = markService.getAutomated(markRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/adding/Mark")
    public ResponseEntity<ArrayList> adding(@RequestBody MarkRequest markRequest) {
        ArrayList answer = new ArrayList();
        answer.add(markService.saveMark(Long.parseLong(markRequest.getId()), Integer.parseInt(markRequest.getDiaryId()), markRequest.getDate(), Integer.parseInt(markRequest.getSubjectId()), Byte.parseByte(markRequest.getMark())));
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/delete/Mark")
    public void delete(@RequestBody MarkRequest markRequest) {
        markService.deleteMark(markService.getMarkByMarkid(Long.parseLong(markRequest.getId())));
    }

    @PostMapping("/api/vi/modify/Mark")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody MarkRequest markRequest) {
        Mark mark = markService.getMarkByMarkid(Long.parseLong(markRequest.getId()));
        mark.setDiaryID(Integer.parseInt(markRequest.getDiaryId()));
        mark.setSubjectID(Integer.parseInt(markRequest.getSubjectId()));
        mark.setDate(markRequest.getDate());
        mark.setMark(Byte.parseByte(markRequest.getMark()));
        markService.saveMark(mark);
        ArrayList answer = new ArrayList();
        answer.add(markService.getMarkByMarkid(mark.getId()));
        return ResponseEntity.ok(answer);
    }

}
