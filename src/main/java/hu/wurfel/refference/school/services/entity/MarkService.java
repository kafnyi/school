package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MarkService extends MarkCrudService {
	@Autowired
	private ClassService cs;
	@Autowired
	private DiaryService ds;
	@Autowired
	private StudentService ss;
	@Autowired
	private SubjectService sjs;
	@Autowired
	private TeacherService ts;

	public ArrayList<Mark> getMarksByDiary(Diary diary) {
		return getMarksByDiaryid(diary.getId());
	}

	public ArrayList<Mark> getMarksByStudent(Student student) {
		ArrayList<Mark> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByScid(student.getId()))) {
			result.addAll(getMarksByDiaryid(diary.getId()));
		}
		return result;
	}

	public ArrayList<Mark> getMarksByClass(Class division) {
		ArrayList<Mark> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByClassid(division.getId()))) {
			result.addAll(getMarksByDiaryid(diary.getId()));
		}
		return result;
	}

	public ArrayList<Mark> getMarksBySubject(Subject subject) {
		return getMarksBySubjectid(subject.getId());
	}

	public ArrayList<Mark> getMarksByTeacher(Teacher teacher) {
		ArrayList<Mark> result = new ArrayList<>();
		for (Subject subject : new ArrayList<Subject>(sjs.getSubjectsByTid(teacher.getId()))) {
			result.addAll(getMarksBySubjectid(subject.getId()));
		}
		return result;
	}
}
