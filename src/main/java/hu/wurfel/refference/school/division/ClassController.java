package hu.wurfel.refference.school.division;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.ArrayList;

@RestController
public class ClassController {


    @Autowired
    ClassService classService;

    @PostMapping("/api/v1/search/Class")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody ClassRequest classRequest) {
        ArrayList answer = new ArrayList();
        answer = classService.getAutomated(classRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/adding/Class")
    public ResponseEntity<ArrayList> adding(@RequestBody ClassRequest classRequest) {
        ArrayList answer = new ArrayList();
        answer.add(classService.saveClass(Integer.parseInt(classRequest.getId()), Short.parseShort(classRequest.getGrade()), classRequest.getSign().strip().charAt(0), Year.parse(classRequest.getYear()), Long.parseLong(classRequest.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/delete/Class")
    public void delete(@RequestBody ClassRequest classRequest) {
        classService.deleteClass(classService.getClassByClassId(Integer.parseInt(classRequest.getId())));
    }

    @PostMapping("/api/vi/modify/Class")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody ClassRequest classRequest) {
        Class division = classService.getClassByClassId(Integer.parseInt(classRequest.getId()));
        division.setId(Integer.parseInt(classRequest.getId()));
        division.setGrade(Short.parseShort(classRequest.getGrade()));
        division.setSign(classRequest.getSign().strip().charAt(0));
        division.setYear(Year.parse(classRequest.getYear()));
        classService.saveClass(division);
        ArrayList answer = new ArrayList();
        answer.add(classService.getClassByClassId(division.getId()));
        return ResponseEntity.ok(answer);
    }

}
