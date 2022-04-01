package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentCrudService {

	@Autowired
	private StudentRepository studentRepository;

	public Iterable<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public Student getStudentById(Long Id) {
		return studentRepository.findByID(Id);
	}

	public Student getStudentByName(String name) {
		return studentRepository.findBySName(name);
	}

	public Student getStudentBySBirth(Date date) {
		return studentRepository.findBySBirth(date);
	}

	public Student getStudentByDiary(int diary) {
		return studentRepository.findBySDiaryID(diary);
	}

	public Student addStudent(Student student) {
		studentRepository.save(student);
		return studentRepository.findByID(student.getID());
	}

	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

}
