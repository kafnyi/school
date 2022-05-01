package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.SubjectCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService extends SubjectCrudService {
	@Autowired
	private ClassService cs;
	@Autowired
	private DiaryService ds;
	@Autowired
	private MarkService ms;
	@Autowired
	private StudentService ss;
	@Autowired
	private TeacherService ts;

	public Subject getSubjectByMark(Mark mark) {
		return getSubjectBySubjectId(mark.getSubjectID());
	}

	public ArrayList<Subject> getSubjectsByTeacher(Teacher teacher) {
		return getSubjectsByTid(teacher.getId());
	}

	public ArrayList<Subject> getSubjectsByDiary(Diary diary) {
		ArrayList<Subject> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(ms.getMarksByDiaryid(diary.getId()))) {
			result.add(getSubjectBySubjectId(mark.getSubjectID()));
		}
		return result;
	}

	public ArrayList<Subject> getSubjectsByStudent(Student student) {
		ArrayList<Subject> result = new ArrayList<>();
		{
			for (Diary diary : new ArrayList<Diary>(ds.getDiariesByScid(student.getId())))
				for (Mark mark : new ArrayList<Mark>(ms.getMarksByDiaryid(diary.getId()))) {
					result.add(getSubjectBySubjectId(mark.getSubjectID()));
				}
		}
		return result;
	}

	public ArrayList<Subject> getSubjectsByClass(Class division) {
		ArrayList<Subject> result = new ArrayList<>();
		{
			for (Diary diary : new ArrayList<Diary>(ds.getDiariesByClassid(division.getId())))
				for (Mark mark : new ArrayList<Mark>(ms.getMarksByDiaryid(diary.getId()))) {
					result.add(getSubjectBySubjectId(mark.getSubjectID()));
				}
		}
		return result;
	}

}
