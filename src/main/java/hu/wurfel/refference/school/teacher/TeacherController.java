package hu.wurfel.refference.school.teacher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/v1/Teacher")
public class TeacherController {

    TeacherService teacherService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody TeacherRequest teacherRequest) {
        ArrayList answer = new ArrayList();
        answer = teacherService.getAutomated(teacherRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody TeacherRequest teacherRequest) {
        ArrayList answer = new ArrayList();
        answer.add(teacherService.saveTeacher(Long.parseLong(teacherRequest.getId()), teacherRequest.getName(), teacherRequest.getDate()));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        teacherService.deleteTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = teacherService.getTeacherByTeacherId(Long.parseLong(teacherRequest.getId()));
        teacher.setName(teacherRequest.getName());
        teacher.setBirthDate(teacherRequest.getDate());
        teacherService.saveTeacher(teacher);
        ArrayList answer = new ArrayList();
        answer.add(teacherService.getTeacherByTeacherId(teacher.getId()));
        return ResponseEntity.ok(answer);
    }

}
