package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Student")
public class StudentController {


    StudentService studentService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Student>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(studentService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping
    public ResponseEntity<List<Student>> create(@RequestBody StudentRequestForSearch studentRequestForSearch) {
        return ResponseEntity.ok(studentService.save(Long.parseLong(studentRequestForSearch.getId()), studentRequestForSearch.getName(), studentRequestForSearch.getDate()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        studentService.delete(studentService.getByStudentId(Long.parseLong(id)));
    }

    @PutMapping
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
