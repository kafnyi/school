package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Teacher;
import hu.wurfel.refference.school.repositories.TeacherRepository;
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

	public Teacher getTeacherById(Long Id) {
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

	public Teacher addTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getID());
	}

	public Teacher addTeacher(Long id, String name, Date birth, int diary) {
		Teacher teacher = new Teacher(id, name, birth, diary);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getID());
	}

	public Teacher setTeacherID(Teacher teacher, Long id) {
		teacher.setID(id);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getID());
	}

	public Teacher setTeacherName(Teacher teacher, String name) {
		teacher.setName(name);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getID());
	}

	public Teacher setTeacherBirth(Teacher teacher, Date date) {
		teacher.setBirth(date);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getID());
	}

	public Teacher setTeacherDiaryID(Teacher teacher, int id) {
		teacher.setDiaryID(id);
		teacherRepository.save(teacher);
		return teacherRepository.findByID(teacher.getID());
	}

	public void deleteTeacher(Teacher teacher) {
		teacherRepository.delete(teacher);
	}

}
