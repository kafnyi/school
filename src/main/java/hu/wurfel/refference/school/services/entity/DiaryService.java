package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DiaryService extends DiaryCrudService {
	@Autowired
	private ClassService cs;
	@Autowired
	private MarkService ms;

	public ArrayList<Diary> getDiariesByStudent(@NotNull Student student) {
		return getDiariesByScid(student.getId());
	}

	public ArrayList<Diary> getDiariesByClass(@NotNull Class division) {
		return getDiariesByClassid(division.getId());
	}

	public Diary getDiaryByMark(@NotNull Mark mark) {
		return getDiaryByDiaryid(mark.getDiaryID());
	}

	public ArrayList<Diary> getDiariesBySubject(@NotNull Subject subject) {
		ArrayList<Diary> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(ms.getMarksBySubjectid(subject.getId()))) {
			result.add(getDiaryByMark(mark));
		}
		return result;
	}

	public ArrayList<Diary> getDiariesByTeacher(@NotNull Teacher teacher) {
		ArrayList<Diary> result = new ArrayList<>();
		for (Class division : new ArrayList<Class>(cs.getClassesByTid(teacher.getId()))) {
			result.addAll(getDiariesByClass(division));
		}
		return result;
	}
}
