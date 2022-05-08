package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MarkService extends MarkCrudService {
	@Autowired
	private DiaryService ds;
	@Autowired
	private SubjectService sjs;

	public ArrayList<Mark> getMarksByDiary(@NotNull Diary diary) {
		return getMarksByDiaryid(diary.getId());
	}

	public ArrayList<Mark> getMarksByStudent(@NotNull Student student) {
		ArrayList<Mark> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByScid(student.getId()))) {
			result.addAll(getMarksByDiary(diary));
		}
		return result;
	}

	public ArrayList<Mark> getMarksByClass(@NotNull Class division) {
		ArrayList<Mark> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByClassid(division.getId()))) {
			result.addAll(getMarksByDiary(diary));
		}
		return result;
	}

	public ArrayList<Mark> getMarksBySubject(@NotNull Subject subject) {
		return getMarksBySubjectid(subject.getId());
	}

	public ArrayList<Mark> getMarksByTeacher(@NotNull Teacher teacher) {
		ArrayList<Mark> result = new ArrayList<>();
		for (Subject subject : new ArrayList<Subject>(sjs.getSubjectsByTid(teacher.getId()))) {
			result.addAll(getMarksBySubject(subject));
		}
		return result;
	}
}
