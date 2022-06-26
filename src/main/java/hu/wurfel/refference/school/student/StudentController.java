package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/v1/Student")
public class StudentController {


    StudentService studentService;

    @GetMapping("/search/{searchWith}/{searchBy}/{value}}")
    public ResponseEntity<List<Student>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        List<Student> answer;
        answer = getSearchResponseList(searchWith, searchBy, value);
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

    private List<Student> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return studentService.sWStudent(searchBy, value);
            }
            case Diary -> {
                return studentService.sWDiary(searchBy, value);
            }
            case Class -> {
                return studentService.sWClass(searchBy, value);
            }
            case Subject -> {
                return studentService.sWSubject(searchBy, value);
            }
            case Mark -> {
                return studentService.sWMark(searchBy, value);
            }
            case Teacher -> {
                return studentService.sWTeacher(searchBy, value);
            }
            default -> {
                return null;
            }
        }
    }

}
