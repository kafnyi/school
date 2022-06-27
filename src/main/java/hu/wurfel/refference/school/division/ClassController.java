package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClassController {

    ClassService classService;

    @GetMapping("/api/vi/Class/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Class>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        List<Class> answer;
        answer = getSearchResponseList(searchWith, searchBy, value);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/vi/Class/adding")
    public ResponseEntity<List<Class>> adding(@RequestBody ClassRequestForSearch classRequestForSearch) {
        List<Class> answer = new ArrayList<>();
        answer.add(classService.save(Integer.parseInt(classRequestForSearch.getId()), Short.parseShort(classRequestForSearch.getGrade()), classRequestForSearch.getSign().strip().charAt(0), Year.parse(classRequestForSearch.getYear()), Long.parseLong(classRequestForSearch.getTeacherId())));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/api/vi/Class/delete/{id}")
    public void delete(@PathVariable String id) {
        classService.delete(classService.getByClassId(Integer.parseInt(id)));
    }

    @PutMapping("/api/vi/Class/api/vi/modify/Class")
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

    private List<Class> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return classService.cWStudent(searchBy, value);
            }
            case Diary -> {
                return classService.cWDiary(searchBy, value);
            }
            case Class -> {
                return classService.cWClass(searchBy, value);
            }
            case Subject -> {
                return classService.cWSubject(searchBy, value);
            }
            case Mark -> {
                return classService.cWMark(searchBy, value);
            }
            case Teacher -> {
                return classService.cWTeacher(searchBy, value);
            }
            default -> {
                return null;
            }
        }
    }

}
