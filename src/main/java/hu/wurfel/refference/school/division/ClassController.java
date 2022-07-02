package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<List<Class>> create(@RequestBody ClassDto classDto) {
        return ResponseEntity.ok(classService.save(classDto.getId(), classDto.getGrade(), classDto.getSign(), classDto.getYear(), classDto.getTeacherId()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        classService.delete(classService.getByClassId(Integer.parseInt(id)));
    }

    @PutMapping
    public ResponseEntity<List<Class>> modify(@RequestBody ClassDto classDto) {
        Class division = classService.getByClassId(classDto.getId());
        division.setGrade(classDto.getGrade());
        division.setSign(classDto.getSign());
        division.setYear(classDto.getYear());
        classService.save(division);
        List<Class> answer = new ArrayList<>();
        answer.add(classService.getByClassId(division.getId()));
        return ResponseEntity.ok(answer);
    }



}
