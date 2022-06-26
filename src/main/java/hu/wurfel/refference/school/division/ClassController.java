package hu.wurfel.refference.school.division;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;

@RestController("/api/vi/Class")
public class ClassController {


    ClassService classService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody ClassRequest classRequest) {
        ArrayList answer = new ArrayList();
        answer = classService.getAutomated(classRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody ClassRequest classRequest) {
        ArrayList answer = new ArrayList();
        answer.add(classService.saveClass(Integer.parseInt(classRequest.getId()), Short.parseShort(classRequest.getGrade()), classRequest.getSign().strip().charAt(0), Year.parse(classRequest.getYear()), Long.parseLong(classRequest.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        classService.deleteClass(classService.getClassByClassId(Integer.parseInt(id)));
    }

    @PutMapping("/api/vi/modify/Class")
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
