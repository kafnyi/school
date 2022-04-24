package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Teacher;
import hu.wurfel.refference.school.repositories.TeacherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TeacherCrudService {

	@Autowired
	private TeacherRepository teacherRepository;

	public Iterable<Teacher> getAllTeacher() {
		return teacherRepository.findAll();
	}

	public Teacher getTeacherById(long Id) {
		return teacherRepository.findByID(Id);
	}

	public Teacher getTeacherByName(String name) {
		return teacherRepository.findByTName(name);
	}

	public Teacher getTeacherByDiary(int diary) {
		return teacherRepository.findByTDiaryID(diary);
	}

	public Teacher getTeacherByBirth(Date date) {
		return teacherRepository.findByTBirth(date);
	}

	public Teacher addTeacher(@NotNull Teacher teacher) {
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getId());
	}

	public Teacher addTeacher(long id, String name, Date birth) {
		Teacher teacher = new Teacher(id, name, birth);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getId());
	}

	public Teacher setTeacherID(@NotNull Teacher teacher, long id) {
		teacher.setId(id);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getId());
	}

	public Teacher setTeacherName(@NotNull Teacher teacher, String name) {
		teacher.setName(name);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getId());
	}

	public Teacher setTeacherBirth(@NotNull Teacher teacher, Date date) {
		teacher.setBirthDate(date);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getId());
	}

	public void deleteTeacher(Teacher teacher) {
		teacherRepository.delete(teacher);
	}
}
