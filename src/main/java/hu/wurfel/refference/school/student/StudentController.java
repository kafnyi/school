package hu.wurfel.refference.school.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/v1/Student")
public class StudentController {


    StudentService studentService;

    @PostMapping("/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody StudentRequest studentRequest) {
        ArrayList answer = new ArrayList();
        answer = studentService.getAutomated(studentRequest);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody StudentRequest studentRequest) {
        ArrayList answer = new ArrayList();
        answer.add(studentService.saveStudent(Long.parseLong(studentRequest.getId()), studentRequest.getName(), studentRequest.getDate()));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        studentService.deleteStudent(studentService.getStudentByStudentId(Long.parseLong(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<ArrayList> modifyStudent(@RequestBody StudentRequest studentRequest) {
        Student student = studentService.getStudentByStudentId(Long.parseLong(studentRequest.getId()));
        student.setName(studentRequest.getName());
        student.setBirthDate(studentRequest.getDate());
        studentService.saveStudent(student);
        ArrayList answer = new ArrayList();
        answer.add(studentService.getStudentByStudentId(student.getId()));
        return ResponseEntity.ok(answer);
    }

}
