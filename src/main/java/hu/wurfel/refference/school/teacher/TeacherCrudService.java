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
        return teacherRepository.findAll();
    }

    public Teacher getByTeacherId(Long Id) {
        return teacherRepository.findById(Id).get();
    }

    public List<Teacher> getAllByName(String name) {
        return teacherRepository.findAllByName(name);
    }

    public List<Teacher> getAllByBirth(String date) {
        return teacherRepository.findAllByBirthDate(date);
    }

    public Teacher save(@NotNull Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> save(Long id, String name, String birth) {
        ArrayList<Teacher> saved = new ArrayList<>();
        saved.add(save(new Teacher(id, name, birth)));
        return saved;
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
