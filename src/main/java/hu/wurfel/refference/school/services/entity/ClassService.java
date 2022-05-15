package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.ClassCrudService;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassService extends ClassCrudService {

	@Autowired
	private MarkCrudService markCrudService;
	@Autowired
	private DiaryCrudService diaryCrudService;

	public Class getClassByDiary(@NotNull Diary diary) {
		return getClassByClassId(diary.getClassID());
	}

	public Class getClassByMark(@NotNull Mark mark) {
		return getClassByClassId((diaryCrudService.getDiaryByDiaryid(mark.getDiaryID())).getClassID());
	}

	public ArrayList<Class> getClassesByTeacher(@NotNull Teacher teacher) {
		return getClassesByTid(teacher.getId());
	}

	public ArrayList<Class> getClassesByStudent(@NotNull Student student) {
		ArrayList<Class> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getId()))) {
			result.add(getClassByDiary(diary));
		}
		return result;
	}

	public ArrayList<Class> getClassesBySubject(@NotNull Subject subject) {
		ArrayList<Class> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
			result.add(getClassByMark(mark));
		}
		return result;
	}


}
