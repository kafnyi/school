package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {

    TeacherService teacherService;

    @GetMapping("/api/v1/Teacher/search/{searchWith}/{searchBy}/{value}")
    public ResponseEntity<List<Teacher>> searchForStudent(@PathVariable EntityNames searchWith, @PathVariable EntityFieldNames searchBy, @PathVariable String value) {
        List<Teacher> answer;
        answer = getSearchResponseList(searchWith, searchBy, value);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/api/v1/Teacher/adding")
    public ResponseEntity<List<Teacher>> adding(@RequestBody TeacherRequestForSearch teacherRequestForSearch) {
        List<Teacher> answer = new ArrayList<>();
        answer.add(teacherService.save(Long.parseLong(teacherRequestForSearch.getId()), teacherRequestForSearch.getName(), teacherRequestForSearch.getDate()));
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/api/v1/Teacher/delete/{id}")
    public void delete(@PathVariable String id) {
        teacherService.delete(teacherService.getByTeacherId(Long.parseLong(id)));
    }

    @PutMapping("/api/v1/Teacher/modify")
    public ResponseEntity<List<Teacher>> modifyStudent(@RequestBody TeacherRequestForSearch teacherRequestForSearch) {
        Teacher teacher = teacherService.getByTeacherId(Long.parseLong(teacherRequestForSearch.getId()));
        teacher.setName(teacherRequestForSearch.getName());
        teacher.setBirthDate(teacherRequestForSearch.getDate());
        teacherService.save(teacher);
        List<Teacher> answer = new ArrayList<>();
        answer.add(teacherService.getByTeacherId(teacher.getId()));
        return ResponseEntity.ok(answer);
    }

    private List<Teacher> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return teacherService.tWStudent(searchBy, value);
            }
            case Diary -> {
                return teacherService.tWDiary(searchBy, value);
            }
            case Class -> {
                return teacherService.tWClass(searchBy, value);
            }
            case Subject -> {
                return teacherService.tWSubject(searchBy, value);
            }
            case Mark -> {
                return teacherService.tWMark(searchBy, value);
            }
            case Teacher -> {
                return teacherService.tWTeacher(searchBy, value);
            }
            default -> {
                return null;
            }
        }
    }

}
