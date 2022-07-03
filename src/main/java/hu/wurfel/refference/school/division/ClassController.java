package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Class")
public class ClassController {

    ClassService classService;

    @GetMapping("/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<ClassDto>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(classService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping
    public ResponseEntity<List<ClassDto>> create(@RequestBody ClassDto classDto) {
        return ResponseEntity.ok(classService.getDtoList(classService.save(classDto.getId(), classDto.getGrade(), classDto.getSign(), classDto.getYear(), classDto.getTeacherId())));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        classService.delete(classService.getByClassId(Integer.parseInt(id)));
    }

    @PutMapping
    public ResponseEntity<List<ClassDto>> modify(@RequestBody ClassDto classDto) {
        return ResponseEntity.ok(classService.modify(classDto));
    }



}
