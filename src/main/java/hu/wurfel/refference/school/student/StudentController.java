package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Student")
public class StudentController {


    StudentService studentService;

    @GetMapping("/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<StudentDto>> search(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(studentService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping
    public ResponseEntity<List<StudentDto>> create(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.getDtoList(studentService.save(studentDto.getId(), studentDto.getName(), studentDto.getBirthDate())));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        studentService.delete(studentService.getById(Long.parseLong(id)));
    }

    @PutMapping
    public ResponseEntity<List<StudentDto>> modify(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.modify(studentDto));
    }



}
