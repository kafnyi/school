package hu.wurfel.refference.school.teacher;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherCrudService {

    private final TeacherRepository teacherRepository;

    public TeacherCrudService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Iterable<Teacher> getAllTeacher() {
        return new ArrayList<>(teacherRepository.findAll());
    }

    public Teacher getTeacherByTeacherId(Long Id) {
        return teacherRepository.findById(Id).get();
    }

    public List<Teacher> getTeacherByName(String name) {
        return teacherRepository.findAllByName(name);
    }

    public List<Teacher> getTeacherByBirth(String date) {
        return teacherRepository.findAllByBirthDate(date);
    }

    public Teacher saveTeacher(@NotNull Teacher teacher) {
        teacherRepository.save(teacher);
        return getTeacherByTeacherId(teacher.getId());
    }

    public Teacher saveTeacher(Long id, String name, String birth) {
        return saveTeacher(new Teacher(id, name, birth));
    }

    public Teacher setTeacherID(@NotNull Teacher teacher, long id) {
        teacher.setId(id);
        return saveTeacher(teacher);
    }

    public Teacher setTeacherName(@NotNull Teacher teacher, String name) {
        teacher.setName(name);
        return saveTeacher(teacher);
    }

    public Teacher setTeacherBirth(@NotNull Teacher teacher, String date) {
        teacher.setBirthDate(date);
        return saveTeacher(teacher);
    }

    public void deleteTeacher(@NotNull Teacher teacher) {
        teacherRepository.delete(teacher);
    }
}
