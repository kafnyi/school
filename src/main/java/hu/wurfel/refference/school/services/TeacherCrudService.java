package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Teacher;
import hu.wurfel.refference.school.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherCrudService {

	@Autowired
	private TeacherRepository teacherRepository;

	public Iterable<Teacher> getAllTeacher() {
		return teacherRepository.FindAll();
	}

	public Teacher getTeacherById(Long Id) {
		return teacherRepository.FindById(Id);
	}

	public Teacher getTeacherByName(String Name) {
		return teacherRepository.FindByName(Name);
	}

	public Teacher getTeacherByDiary(int Diary) {
		return teacherRepository.FindByDiary(Diary);
	}

	public Teacher addTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
		return teacherRepository.FindById(teacher.getID());
	}

	public void deleteTeacher(Teacher teacher) {
		teacherRepository.delete(teacher);
	}

}
