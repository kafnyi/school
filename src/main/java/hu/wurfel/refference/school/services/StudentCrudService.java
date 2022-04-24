package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.repositories.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class StudentCrudService {

	@Autowired
	private StudentRepository studentRepository;

	public ArrayList<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public Student getStudentById(long Id) {
		return studentRepository.findByID(Id);
	}

	public Student getStudentByName(String name) {
		return studentRepository.findBySName(name);
	}

	public Student getStudentByBirth(String date) {
		return studentRepository.findBySBirth(date);
	}

	public Student addStudent(@NotNull Student student) {
		studentRepository.save(student);
		return studentRepository.findByID(student.getId());
	}

	public Student addStudent(long id, String name, Date birth) {
		Student student = new Student(id, name, birth);
		studentRepository.save(student);
		return studentRepository.findByID(student.getId());
	}

	public Student setStudentID(@NotNull Student student, long id) {
		student.setId(id);
		studentRepository.save(student);
		return studentRepository.findByID(student.getId());
	}

	public Student setStudentName(@NotNull Student student, String name) {
		student.setName(name);
		studentRepository.save(student);
		return studentRepository.findByID(student.getId());
	}

	public Student setStudentBirth(@NotNull Student student, Date date) {
		student.setBirthDate(date);
		studentRepository.save(student);
		return studentRepository.findByID(student.getId());
	}

	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}
}
