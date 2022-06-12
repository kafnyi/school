package hu.wurfel.refference.school.services.entity.cruds;

import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.repositories.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentCrudService {

	@Autowired
	public StudentRepository studentRepository;

	public ArrayList<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student getStudentByStudentId(long id) {
		return studentRepository.findById(id).get();
	}

	public ArrayList<Student> getStudentsByName(String name) {
		return studentRepository.findAllByName(name);
	}

	public ArrayList<Student> getStudentsByBirth(String date) {
		return studentRepository.findAllByBirthDate(date);
	}

	public Student getStudentByNameAndBirth(String name, String birth) {
		return studentRepository.findByNameAndBirthDate(name, birth).get();
	}

	public Student saveStudent(@NotNull Student student) {
		studentRepository.save(student);
		return getStudentByStudentId(student.getId());
	}

	public Student saveStudent(long id, String name, String birth) {
		return saveStudent(new Student(id, name, birth));
	}

	public Student setStudentID(@NotNull Student student, long id) {
		student.setId(id);
		return saveStudent(student);
	}

	public Student setStudentName(@NotNull Student student, String name) {
		student.setName(name);
		return saveStudent(student);
	}

	public Student setStudentBirth(@NotNull Student student, String date) {
		student.setBirthDate(date);
		return saveStudent(student);
	}

	public void deleteStudent(@NotNull Student student) {
		studentRepository.delete(student);
	}
}
