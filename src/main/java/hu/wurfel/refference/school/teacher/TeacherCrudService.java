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

    public Iterable<Teacher> getAll() {
        return new ArrayList<>(teacherRepository.findAll());
    }

    public Teacher getByTeacherId(Long Id) {
        return teacherRepository.findById(Id).get();
    }

    public List<Teacher> getByName(String name) {
        return teacherRepository.findAllByName(name);
    }

    public List<Teacher> getByBirth(String date) {
        return teacherRepository.findAllByBirthDate(date);
    }

    public Teacher save(@NotNull Teacher teacher) {
        teacherRepository.save(teacher);
        return getByTeacherId(teacher.getId());
    }

    public Teacher save(Long id, String name, String birth) {
        return save(new Teacher(id, name, birth));
    }

    public Teacher setID(@NotNull Teacher teacher, long id) {
        teacher.setId(id);
        return save(teacher);
    }

    public Teacher setName(@NotNull Teacher teacher, String name) {
        teacher.setName(name);
        return save(teacher);
    }

    public Teacher setBirth(@NotNull Teacher teacher, String date) {
        teacher.setBirthDate(date);
        return save(teacher);
    }

    public void delete(@NotNull Teacher teacher) {
        teacherRepository.delete(teacher);
    }
}
