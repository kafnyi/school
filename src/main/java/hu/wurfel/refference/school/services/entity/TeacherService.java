package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import hu.wurfel.refference.school.services.entity.cruds.SubjectCrudService;
import hu.wurfel.refference.school.services.entity.cruds.TeacherCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService extends TeacherCrudService {
	@Autowired
	private DiaryCrudService diaryCrudService;
	@Autowired
	private MarkCrudService markCrudService;
	@Autowired
	private SubjectCrudService subjectCrudService;

	public Teacher getTeacherByClass(@NotNull Class division) {
		return getTeacherByTeacherId(division.getTid());
	}

	public Teacher getTeacherBySubject(@NotNull Subject subject) {
		return getTeacherByTeacherId(subject.getTid());
	}

	public Teacher getTeacherByMark(@NotNull Mark mark) {
		return getTeacherBySubject(subjectCrudService.getSubjectBySubjectId(mark.getSubjectID()));
	}

	public ArrayList<Teacher> getTeachersByDiary(@NotNull Diary diary) {
		ArrayList<Teacher> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksByDiaryid(diary.getId()))) {
			result.add(getTeacherByMark(mark));
		}
		return result;
	}

	public ArrayList<Teacher> getTeacherByStudent(@NotNull Student student) {
		ArrayList<Teacher> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getId()))) {
			result.addAll(getTeachersByDiary(diary));
		}
		return result;
	}
}
