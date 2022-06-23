package hu.wurfel.refference.school.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TeacherController {

    @Autowired
    ResponseCreationClarificationTeacher responseCreationClarificationTeacher;

    @Autowired
    TeacherService teacherService;

    @PostMapping("/api/v1/search/Teacher")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody TeacherRequest teacherRequest) {
        ArrayList answer = new ArrayList();
        answer = responseCreationClarificationTeacher.create(teacherRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/adding/Teacher")
    public ResponseEntity<ArrayList> adding(@RequestBody TeacherRequest teacherRequest) {
        ArrayList answer = new ArrayList();
        answer.add(teacherService.saveTeacher(Long.parseLong(teacherRequest.getId()), teacherRequest.getName(), teacherRequest.getDate()));
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/delete/Teacher")
    public void delete(@RequestBody TeacherRequest teacherRequest) {
        teacherService.deleteTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(teacherRequest.getId())));
    }

    @PostMapping("/api/vi/modify/Teacher")
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
