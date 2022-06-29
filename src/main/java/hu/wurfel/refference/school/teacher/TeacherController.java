package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Teacher/")
public class TeacherController {

    TeacherService teacherService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Teacher>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(teacherService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping("/adding")
    public ResponseEntity<List<Teacher>> create(@RequestBody TeacherRequestForSearch teacherRequestForSearch) {
        return ResponseEntity.ok(teacherService.save(Long.parseLong(teacherRequestForSearch.getId()), teacherRequestForSearch.getName(), teacherRequestForSearch.getDate()));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        teacherService.delete(teacherService.getByTeacherId(Long.parseLong(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<List<Teacher>> modifyStudent(@RequestBody TeacherRequestForSearch teacherRequestForSearch) {
        Teacher teacher = teacherService.getByTeacherId(Long.parseLong(teacherRequestForSearch.getId()));
        teacher.setName(teacherRequestForSearch.getName());
        teacher.setBirthDate(teacherRequestForSearch.getDate());
        teacherService.save(teacher);
        List<Teacher> answer = new ArrayList<>();
        answer.add(teacherService.getByTeacherId(teacher.getId()));
        return ResponseEntity.ok(answer);
    }


}
