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
        answer = getSearchResponseList(searchWith, searchBy, value);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/Student/adding")
    public ResponseEntity<List<Student>> adding(@RequestBody StudentRequestForSearch studentRequestForSearch) {
        List<Student> answer = new ArrayList<>();
        answer.add(studentService.save(Long.parseLong(studentRequestForSearch.getId()), studentRequestForSearch.getName(), studentRequestForSearch.getDate()));
        return ResponseEntity.ok(answer);
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

    private List<Student> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return studentService.searchWithStudent(searchBy, value);
            }
            case Diary -> {
                return studentService.searchWithDiary(searchBy, value);
            }
            case Class -> {
                return studentService.searchWithClass(searchBy, value);
            }
            case Subject -> {
                return studentService.searchWithSubject(searchBy, value);
            }
            case Mark -> {
                return studentService.searchWithMark(searchBy, value);
            }
            case Teacher -> {
                return studentService.searchWithTeacher(searchBy, value);
            }
            default -> {
                return null;
            }
        }
    }

}
