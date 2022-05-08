package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.SubjectCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService extends SubjectCrudService {
	@Autowired
	private DiaryService ds;
	@Autowired
	private MarkService ms;

	public Subject getSubjectByMark(@NotNull Mark mark) {
		return getSubjectBySubjectId(mark.getSubjectID());
	}

	public ArrayList<Subject> getSubjectsByTeacher(@NotNull Teacher teacher) {
		return getSubjectsByTid(teacher.getId());
	}

	public ArrayList<Subject> getSubjectsByDiary(@NotNull Diary diary) {
		ArrayList<Subject> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(ms.getMarksByDiaryid(diary.getId()))) {
			result.add(getSubjectByMark(mark));
		}
		return result;
	}

	public ArrayList<Subject> getSubjectsByStudent(@NotNull Student student) {
		ArrayList<Subject> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByScid(student.getId()))) {
			result.addAll(getSubjectsByDiary(diary));
		}
		return result;
	}

	public ArrayList<Subject> getSubjectsByClass(@NotNull Class division) {
		ArrayList<Subject> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByClassid(division.getId()))) {
			result.addAll(getSubjectsByDiary(diary));
		}
		return result;
	}

}
