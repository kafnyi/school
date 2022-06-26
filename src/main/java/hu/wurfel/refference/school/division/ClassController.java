package hu.wurfel.refference.school.division;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;

@RestController("/api/vi/Class")
public class ClassController {


    ClassService classService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody ClassRequestForSearch classRequestForSearch) {
        ArrayList answer = new ArrayList();
        answer = classService.getAutomated(classRequestForSearch);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody ClassRequestForSearch classRequestForSearch) {
        ArrayList answer = new ArrayList();
        answer.add(classService.saveClass(Integer.parseInt(classRequestForSearch.getId()), Short.parseShort(classRequestForSearch.getGrade()), classRequestForSearch.getSign().strip().charAt(0), Year.parse(classRequestForSearch.getYear()), Long.parseLong(classRequestForSearch.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        classService.deleteClass(classService.getClassByClassId(Integer.parseInt(id)));
    }

    @PutMapping("/api/vi/modify/Class")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody ClassRequestForSearch classRequestForSearch) {
        Class division = classService.getClassByClassId(Integer.parseInt(classRequestForSearch.getId()));
        division.setId(Integer.parseInt(classRequestForSearch.getId()));
        division.setGrade(Short.parseShort(classRequestForSearch.getGrade()));
        division.setSign(classRequestForSearch.getSign().strip().charAt(0));
        division.setYear(Year.parse(classRequestForSearch.getYear()));
        classService.saveClass(division);
        ArrayList answer = new ArrayList();
        answer.add(classService.getClassByClassId(division.getId()));
        return ResponseEntity.ok(answer);
    }

}
