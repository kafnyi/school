package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/v1/Teacher")
public class TeacherController {

    TeacherService teacherService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}}")
    public ResponseEntity<List<Teacher>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        List<Teacher> answer;
        answer = teacherService.getAutomated(teacherRequestForSearch);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<List<Teacher>> adding(@RequestBody TeacherRequestForSearch teacherRequestForSearch) {
        List<Teacher> answer = new ArrayList<>();
        answer.add(teacherService.saveTeacher(Long.parseLong(teacherRequestForSearch.getId()), teacherRequestForSearch.getName(), teacherRequestForSearch.getDate()));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        teacherService.deleteTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<List<Teacher>> modifyStudent(@RequestBody TeacherRequestForSearch teacherRequestForSearch) {
        Teacher teacher = teacherService.getTeacherByTeacherId(Long.parseLong(teacherRequestForSearch.getId()));
        teacher.setName(teacherRequestForSearch.getName());
        teacher.setBirthDate(teacherRequestForSearch.getDate());
        teacherService.saveTeacher(teacher);
        List<Teacher> answer = new ArrayList<>();
        answer.add(teacherService.getTeacherByTeacherId(teacher.getId()));
        return ResponseEntity.ok(answer);
    }

}
