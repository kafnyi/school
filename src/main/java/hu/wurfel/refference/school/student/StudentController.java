package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {


    StudentService studentService;

    @GetMapping("/api/v1/Student/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Student>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        List<Student> answer;
        answer = studentService.getSearchResponseList(searchWith, searchBy, value);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/Student/adding")
    public ResponseEntity<List<Student>> create(@RequestBody StudentRequestForSearch studentRequestForSearch) {
        return ResponseEntity.ok(studentService.save(Long.parseLong(studentRequestForSearch.getId()), studentRequestForSearch.getName(), studentRequestForSearch.getDate()));
    }

    @DeleteMapping("/api/v1/Student/delete/{id}")
    public void delete(@PathVariable String id) {
        studentService.delete(studentService.getByStudentId(Long.parseLong(id)));
    }

    @PutMapping("/api/v1/Student/modify")
    public ResponseEntity<List<Student>> modifyStudent(@RequestBody StudentRequestForSearch studentRequestForSearch) {
        Student student = studentService.getByStudentId(Long.parseLong(studentRequestForSearch.getId()));
        student.setName(studentRequestForSearch.getName());
        student.setBirthDate(studentRequestForSearch.getDate());
        studentService.save(student);
        List<Student> answer = new ArrayList<>();
        answer.add(studentService.getByStudentId(student.getId()));
        return ResponseEntity.ok(answer);
    }



}
