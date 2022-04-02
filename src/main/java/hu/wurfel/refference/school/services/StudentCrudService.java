package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.repositories.StudentRepository;
import org.jetbrains.annotations.NotNull;
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

	public Student addStudent(@NotNull Student student) {
		studentRepository.save(student);
		return studentRepository.findByID(student.getID());
	}

	public Student addStudent(Long id, String name, Date birth, int diary) {
		Student student = new Student(id, name, birth, diary);
		studentRepository.save(student);
		return studentRepository.findByID(student.getID());
	}

	public Student setStudentID(@NotNull Student student, Long id) {
		student.setID(id);
		studentRepository.save(student);
		return studentRepository.findByID(student.getID());
	}

	public Student setStudentName(@NotNull Student student, String name) {
		student.setName(name);
		studentRepository.save(student);
		return studentRepository.findByID(student.getID());
	}

	public Student setStudentBirth(@NotNull Student student, Date date) {
		student.setSBirth(date);
		studentRepository.save(student);
		return studentRepository.findByID(student.getID());
	}

	public Student setStudentDiaryID(@NotNull Student student, int id) {
		student.setSDiaryID(id);
		studentRepository.save(student);
		return studentRepository.findByID(student.getID());
	}

	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}
}
