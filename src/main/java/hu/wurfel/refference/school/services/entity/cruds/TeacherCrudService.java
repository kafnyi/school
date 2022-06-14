package hu.wurfel.refference.school.services.entity.cruds;

import hu.wurfel.refference.school.model.daos.Teacher;
import hu.wurfel.refference.school.repositories.TeacherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherCrudService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Iterable<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherByTeacherId(Long Id) {
        return teacherRepository.findById(Id).get();
    }

    public ArrayList<Teacher> getTeacherByName(String name) {
        return teacherRepository.findAllByName(name);
    }

    public ArrayList<Teacher> getTeacherByBirth(String date) {
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
