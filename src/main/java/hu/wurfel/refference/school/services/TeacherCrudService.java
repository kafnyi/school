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

	public void deleteTeacher(Teacher teacher) {
		teacherRepository.delete(teacher);
	}

}
