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
		return studentRepository.findBySID(Id).get();
	}

	public ArrayList<Student> getStudentsByName(String name) {
		return studentRepository.findByName(name);
	}

	public ArrayList<Student> getStudentByBirth(Date date) {
		return studentRepository.findByBirthDate(date);
	}

	public Student addStudent(@NotNull Student student) {
		studentRepository.save(student);
		return studentRepository.findBySID(student.getId()).get();
	}

	public Student addStudent(long id, String name, Date birth) {
		Student student = new Student(id, name, birth);
		studentRepository.save(student);
		return studentRepository.findBySID(student.getId()).get();
	}

	public Student setStudentID(@NotNull Student student, long id) {
		student.setId(id);
		studentRepository.save(student);
		return studentRepository.findBySID(student.getId()).get();
	}

	public Student setStudentName(@NotNull Student student, String name) {
		student.setName(name);
		studentRepository.save(student);
		return studentRepository.findBySID(student.getId()).get();
	}

	public Student setStudentBirth(@NotNull Student student, Date date) {
		student.setBirthDate(date);
		studentRepository.save(student);
		return studentRepository.findBySID(student.getId()).get();
	}

	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}
}
