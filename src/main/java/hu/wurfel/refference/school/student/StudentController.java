package hu.wurfel.refference.school.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/v1/Student")
public class StudentController {


    StudentService studentService;

    @PostMapping("/search")
    public ResponseEntity<List<Student>> searchForStudent(@RequestBody StudentRequestForSearch studentRequestForSearch) {
        List<Student> answer;
        answer = studentService.getAutomated(studentRequestForSearch);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/adding")
    public ResponseEntity<List<Student>> adding(@RequestBody StudentRequestForSearch studentRequestForSearch) {
        List<Student> answer = new ArrayList<>();
        answer.add(studentService.saveStudent(Long.parseLong(studentRequestForSearch.getId()), studentRequestForSearch.getName(), studentRequestForSearch.getDate()));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        studentService.deleteStudent(studentService.getStudentByStudentId(Long.parseLong(id)));
    }

    @PutMapping("/modify")
    public ResponseEntity<List<Student>> modifyStudent(@RequestBody StudentRequestForSearch studentRequestForSearch) {
        Student student = studentService.getStudentByStudentId(Long.parseLong(studentRequestForSearch.getId()));
        student.setName(studentRequestForSearch.getName());
        student.setBirthDate(studentRequestForSearch.getDate());
        studentService.saveStudent(student);
        List<Student> answer = new ArrayList<>();
        answer.add(studentService.getStudentByStudentId(student.getId()));
        return ResponseEntity.ok(answer);
    }

}
