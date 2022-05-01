package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.StudentCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService extends StudentCrudService {
	@Autowired
	private ClassService cs;
	@Autowired
	private DiaryService ds;
	@Autowired
	private MarkService ms;
	@Autowired
	private SubjectService sjs;
	@Autowired
	private TeacherService ts;

	public Student getStudentByDiary(Diary diary) {
		return getStudentByStudentId(diary.getScid());
	}

	public Student getStudentByMark(Mark mark) {
		return getStudentByStudentId(ds.getDiaryByDiaryid(mark.getDiaryID()).getId());
	}

	public ArrayList<Student> getStudentsByClass(Class division) {
		ArrayList<Student> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByClassid(division.getId()))) {
			result.add(getStudentByStudentId(diary.getScid()));
		}
		return result;
	}

	public ArrayList<Student> getStudentsBySubject(Subject subject) {
		ArrayList<Student> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(ms.getMarksBySubjectid(subject.getId()))) {
			result.add(getStudentByStudentId(ds.getDiaryByDiaryid(mark.getDiaryID()).getScid()));
		}
		return result;
	}

	public ArrayList<Student> getStudentsByTeacher(Teacher teacher) {
		ArrayList<Student> result = new ArrayList<>();
		for (Class division : new ArrayList<Class>(cs.getClassesByTid(teacher.getId()))) {
			for (Diary diary : new ArrayList<Diary>(ds.getDiariesByClassid(division.getId()))) {
				result.add(getStudentByStudentId(diary.getScid()));
			}
		}
		return result;
	}


}
