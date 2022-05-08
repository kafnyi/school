package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.TeacherCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService extends TeacherCrudService {
	@Autowired
	private DiaryService ds;
	@Autowired
	private MarkService ms;
	@Autowired
	private SubjectService sjs;

	public Teacher getTeacherByClass(@NotNull Class division) {
		return getTeacherByTeacherId(division.getTid());
	}

	public Teacher getTeacherBySubject(@NotNull Subject subject) {
		return getTeacherByTeacherId(subject.getTid());
	}

	public Teacher getTeacherByMark(@NotNull Mark mark) {
		return getTeacherBySubject(sjs.getSubjectBySubjectId(mark.getSubjectID()));
	}

	public ArrayList<Teacher> getTeachersByDiary(@NotNull Diary diary) {
		ArrayList<Teacher> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(ms.getMarksByDiaryid(diary.getId()))) {
			result.add(getTeacherByMark(mark));
		}
		return result;
	}

	public ArrayList<Teacher> getTeacherByStudent(@NotNull Student student) {
		ArrayList<Teacher> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByScid(student.getId()))) {
			result.addAll(getTeachersByDiary(diary));
		}
		return result;
	}
}
