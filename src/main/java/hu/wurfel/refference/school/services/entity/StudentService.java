package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.repositories.StudentRepository;
import hu.wurfel.refference.school.services.entity.cruds.ClassCrudService;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class StudentService {

	StudentRepository studentRepository;
	@Autowired
	private ClassCrudService classCrudService;
	@Autowired
	private DiaryCrudService diaryCrudService;
	@Autowired
	private MarkCrudService markCrudService;

	public Student getStudentByDiary(@NotNull Diary diary) {
		return getStudentByStudentId(diary.getScid());
	}

	public ArrayList<Student> getStudentsByDiaries(@NotNull ArrayList<Diary> diaries) {
		ArrayList<Student> students = new ArrayList<Student>();
		for (Diary diary : diaries) {
			students.add(getStudentByDiary(diary));
		}
		return students;
	}

	public Student getStudentByMark(@NotNull Mark mark) {
		return getStudentByStudentId(diaryCrudService.getDiaryByDiaryid(mark.getDiaryID()).getId());
	}

	public ArrayList<Student> getStudentsByMarks(@NotNull ArrayList<Mark> marks) {
		ArrayList<Student> students = new ArrayList<>();
		for (Mark mark : marks) {
			students.add(getStudentByMark(mark));
		}
		return students;
	}

	public ArrayList<Student> getStudentsByClass(@NotNull Class division) {
		ArrayList<Student> students = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByClassid(division.getId()))) {
			students.add(getStudentByDiary(diary));
		}
		return students;
	}

	public ArrayList<Student> getStudentsByClasses(@NotNull ArrayList<Class> classes) {
		ArrayList<Student> students = new ArrayList<>();
		for (Class division : classes) {
			students.addAll(getStudentsByClass(division));
		}
		return students;
	}

	public ArrayList<Student> getStudentsBySubject(@NotNull Subject subject) {
		ArrayList<Student> students = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
			students.add(getStudentByMark(mark));
		}
		return students;
	}

	public ArrayList<Student> getStudentsBySubjects(@NotNull ArrayList<Subject> subjects) {
		ArrayList<Student> students = new ArrayList<>();
		for (Subject subject : subjects) {
			students.addAll(getStudentsBySubject(subject));
		}
		return students;
	}

	public ArrayList<Student> getStudentsByTeacher(@NotNull Teacher teacher) {
		ArrayList<Student> students = new ArrayList<>();
		for (Class division : new ArrayList<Class>(classCrudService.getClassesByTid(teacher.getId()))) {
			students.addAll(getStudentsByClass(division));
		}
		return students;
	}

	public ArrayList<Student> getStudentsByTeachers(@NotNull ArrayList<Teacher> teachers) {
		ArrayList<Student> students = new ArrayList<>();
		for (Teacher teacher : teachers) {
			students.addAll(getStudentsByTeacher(teacher));
		}
		return students;
	}
	//
	//

	public ArrayList<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student getStudentByStudentId(long id) {
		return studentRepository.findBySID(id);
	}

	public ArrayList<Student> getStudentsByName(String name) {
		return studentRepository.findAllByName(name);
	}

	public ArrayList<Student> getStudentsByBirth(Date date) {
		return studentRepository.findAllByBirthDate(date);
	}

	public Student getStudentByNameAndBirth(String name, Date birth) {
		return studentRepository.findByNameAndBirthDate(name, birth).get();
	}

	public Student saveStudent(@NotNull Student student) {
		studentRepository.save(student);
		return getStudentByStudentId(student.getSID());
	}

	public Student saveStudent(long id, String name, Date birth) {
		return saveStudent(new Student(id, name, birth));
	}

	public Student setStudentID(@NotNull Student student, long id) {
		student.setSID(id);
		return saveStudent(student);
	}

	public Student setStudentName(@NotNull Student student, String name) {
		student.setName(name);
		return saveStudent(student);
	}

	public Student setStudentBirth(@NotNull Student student, Date date) {
		student.setBirthDate(date);
		return saveStudent(student);
	}

	public void deleteStudent(@NotNull Student student) {
		studentRepository.delete(student);
	}
}
