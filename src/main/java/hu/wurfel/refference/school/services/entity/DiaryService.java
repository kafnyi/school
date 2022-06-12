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
public class DiaryService extends DiaryCrudService {
	@Autowired
	private ClassCrudService classCrudService;
	@Autowired
	private MarkCrudService markCrudService;

	public ArrayList<Diary> getDiariesByStudent(@NotNull Student student) {
		return getDiariesByScid(student.getId());
	}

	public ArrayList<Diary> getDiariesByStudents(@NotNull ArrayList<Student> students) {
		ArrayList<Diary> diaries = new ArrayList<>();
		for (Student student : students) {
			diaries.addAll(getDiariesByStudents(students));
		}
		return diaries;
	}

	public ArrayList<Diary> getDiariesByClass(@NotNull Class division) {
		return getDiariesByClassid(division.getId());
	}

	public ArrayList<Diary> getDiariesByClasses(@NotNull ArrayList<Class> classes) {
		ArrayList<Diary> diaries = new ArrayList<>();
		for (Class division : classes) {
			diaries.addAll(getDiariesByClass(division));
		}
		return diaries;
	}

	public Diary getDiaryByMark(@NotNull Mark mark) {
		return getDiaryByDiaryid(mark.getDiaryID());
	}

	public ArrayList<Diary> getDiariesByMarks(@NotNull ArrayList<Mark> marks) {
		ArrayList<Diary> diaries = new ArrayList<>();
		for (Mark mark : marks) {
			diaries.add(getDiaryByMark(mark));
		}
		return diaries;
	}

	public ArrayList<Diary> getDiariesBySubject(@NotNull Subject subject) {
		ArrayList<Diary> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
			result.add(getDiaryByMark(mark));
		}
		return result;
	}

	public ArrayList<Diary> getDiariesBySubjects(@NotNull ArrayList<Subject> subjects) {
		ArrayList<Diary> diaries = new ArrayList<>();
		for (Subject subject : subjects) {
			diaries.addAll(getDiariesBySubject(subject));
		}
		return diaries;
	}

	public ArrayList<Diary> getDiariesByTeacher(@NotNull Teacher teacher) {
		ArrayList<Diary> result = new ArrayList<>();
		for (Class division : new ArrayList<Class>(classCrudService.getClassesByTid(teacher.getId()))) {
			result.addAll(getDiariesByClass(division));
		}
		return result;
	}

	public ArrayList<Diary> getDiariesByTeachers(@NotNull ArrayList<Teacher> teachers) {
		ArrayList<Diary> diaries = new ArrayList<>();
		for (Teacher teacher : teachers) {
			diaries.addAll(getDiariesByTeacher(teacher));
		}
		return diaries;
	}
}
