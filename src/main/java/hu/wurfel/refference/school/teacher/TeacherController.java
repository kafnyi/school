package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Teacher")
public class TeacherController {

    TeacherService teacherService;

    @GetMapping("/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<TeacherDto>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        return ResponseEntity.ok(teacherService.getSearchResponseList(searchWith, searchBy, value));
    }

    @PostMapping
    public ResponseEntity<List<TeacherDto>> create(@RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(teacherService.getDtoList(teacherService.save(teacherDto.getId(), teacherDto.getName(), teacherDto.getBirthDate())));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        teacherService.delete(teacherService.getByTeacherId(Long.parseLong(id)));
    }

    @PutMapping
    public ResponseEntity<List<TeacherDto>> modify(@RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(teacherService.modify(teacherDto));
    }


}
