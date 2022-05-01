package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DiaryService extends DiaryCrudService {
	@Autowired
	private ClassService cs;
	@Autowired
	private MarkService ms;
	@Autowired
	private StudentService ss;
	@Autowired
	private SubjectService sjs;
	@Autowired
	private TeacherService ts;

	public ArrayList<Diary> getDiariesByStudent(Student student) {
		return getDiariesByScid(student.getId());
	}

	public ArrayList<Diary> getDiariesByClass(Class division) {
		return getDiariesByClassid(division.getId());
	}

	public Diary getDiaryByMark(Mark mark) {
		return getDiaryByDiaryid(mark.getDiaryID());
	}

	public ArrayList<Diary> getDiariesBySubject(Subject subject) {
		ArrayList<Diary> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(ms.getMarksBySubjectid(subject.getId()))) {
			result.add(getDiaryByDiaryid(mark.getDiaryID()));
		}
		return result;
	}

	public ArrayList<Diary> getDiariesByTeacher(Teacher teacher) {
		ArrayList<Diary> result = new ArrayList<>();
		for (Class division : new ArrayList<Class>(cs.getClassesByTid(teacher.getId()))) {
			result.addAll(getDiariesByClassid(division.getId()));
		}
		return result;
	}
}
