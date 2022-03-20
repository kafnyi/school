package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCrudService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getStudentList() {
		return studentRepository.FindAll();
	}

	public Student getStudentById(Long Id) {
		return studentRepository.FindById(Id);
	}

	public Student getStudentByName(String Name) {
		return studentRepository.FindByName(Name);
	}

	public Student getStudentByDiary(int Diary) {
		return studentRepository.FindByDiary(Diary);
	}

	public Student addStudent(Student student) {
		studentRepository.save(student);
		return studentRepository.FindById(student.getID());
	}

	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

}
