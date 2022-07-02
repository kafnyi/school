package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Class")
public class ClassController {

    ClassService classService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Class>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(classService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping("/adding")
    public ResponseEntity<List<Class>> create(@RequestBody ClassRequestForSearch classRequestForSearch) {
        return ResponseEntity.ok(classService.save(Integer.parseInt(classRequestForSearch.getId()), Short.parseShort(classRequestForSearch.getGrade()), classRequestForSearch.getSign().strip().charAt(0), Year.parse(classRequestForSearch.getYear()), Long.parseLong(classRequestForSearch.getTeacherId())));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        classService.delete(classService.getByClassId(Integer.parseInt(id)));
    }

    @PutMapping
    public ResponseEntity<List<Class>> modify(@RequestBody ClassRequestForSearch classRequestForSearch) {
        Class division = classService.getByClassId(Integer.parseInt(classRequestForSearch.getId()));
        division.setId(Integer.parseInt(classRequestForSearch.getId()));
        division.setGrade(Short.parseShort(classRequestForSearch.getGrade()));
        division.setSign(classRequestForSearch.getSign().strip().charAt(0));
        division.setYear(Year.parse(classRequestForSearch.getYear()));
        classService.save(division);
        List<Class> answer = new ArrayList<>();
        answer.add(classService.getByClassId(division.getId()));
        return ResponseEntity.ok(answer);
    }



}
