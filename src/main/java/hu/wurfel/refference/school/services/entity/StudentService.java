package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.ClassCrudService;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import hu.wurfel.refference.school.services.entity.cruds.StudentCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService extends StudentCrudService {
	@Autowired
	private ClassCrudService classCrudService;
	@Autowired
	private DiaryCrudService diaryCrudService;
	@Autowired
	private MarkCrudService markCrudService;

	public Student getStudentByDiary(@NotNull Diary diary) {
		return getStudentByStudentId(diary.getScid());
	}

	public Student getStudentByMark(@NotNull Mark mark) {
		return getStudentByStudentId(diaryCrudService.getDiaryByDiaryid(mark.getDiaryID()).getId());
	}

	public ArrayList<Student> getStudentsByClass(@NotNull Class division) {
		ArrayList<Student> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByClassid(division.getId()))) {
			result.add(getStudentByDiary(diary));
		}
		return result;
	}

	public ArrayList<Student> getStudentsBySubject(@NotNull Subject subject) {
		ArrayList<Student> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
			result.add(getStudentByMark(mark));
		}
		return result;
	}

	public ArrayList<Student> getStudentsByTeacher(@NotNull Teacher teacher) {
		ArrayList<Student> result = new ArrayList<>();
		for (Class division : new ArrayList<Class>(classCrudService.getClassesByTid(teacher.getId()))) {
			result.addAll(getStudentsByClass(division));
		}
		return result;
	}


}
