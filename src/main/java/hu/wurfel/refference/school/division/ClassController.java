package hu.wurfel.refference.school.division;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RestController("/api/vi/Class")
public class ClassController {

    ClassService classService;

    @PostMapping("/search")
    public ResponseEntity<List<Class>> search(@RequestBody ClassRequestForSearch classRequestForSearch) {
        List<Class> answer;
        answer = classService.getAutomated(classRequestForSearch);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<List<Class>> adding(@RequestBody ClassRequestForSearch classRequestForSearch) {
        List<Class> answer = new ArrayList<>();
        answer.add(classService.saveClass(Integer.parseInt(classRequestForSearch.getId()), Short.parseShort(classRequestForSearch.getGrade()), classRequestForSearch.getSign().strip().charAt(0), Year.parse(classRequestForSearch.getYear()), Long.parseLong(classRequestForSearch.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        classService.deleteClass(classService.getClassByClassId(Integer.parseInt(id)));
    }

    @PutMapping("/api/vi/modify/Class")
    public ResponseEntity<List<Class>> modify(@RequestBody ClassRequestForSearch classRequestForSearch) {
        Class division = classService.getClassByClassId(Integer.parseInt(classRequestForSearch.getId()));
        division.setId(Integer.parseInt(classRequestForSearch.getId()));
        division.setGrade(Short.parseShort(classRequestForSearch.getGrade()));
        division.setSign(classRequestForSearch.getSign().strip().charAt(0));
        division.setYear(Year.parse(classRequestForSearch.getYear()));
        classService.saveClass(division);
        List<Class> answer = new ArrayList<>();
        answer.add(classService.getClassByClassId(division.getId()));
        return ResponseEntity.ok(answer);
    }

}
