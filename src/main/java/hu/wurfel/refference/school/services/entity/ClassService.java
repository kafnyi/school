package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.ClassCrudService;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassService extends ClassCrudService {

	private MarkCrudService markCrudService;
	private DiaryCrudService diaryCrudService;

	public Class getClassByDiary(@NotNull Diary diary) {
		return getClassByClassId(diary.getClassID());
	}

	public ArrayList<Class> getClassesByDiaries(@NotNull ArrayList<Diary> diaries) {
		ArrayList<Class> classes = new ArrayList<>();
		for (Diary diary : diaries) {
			classes.add(getClassByDiary(diary));
		}
		return classes;
	}

	public Class getClassByMark(@NotNull Mark mark) {
		return getClassByClassId((diaryCrudService.getDiaryByDiaryid(mark.getDiaryID())).getClassID());
	}

	public ArrayList<Class> getClassesByMarks(@NotNull ArrayList<Mark> marks) {
		ArrayList<Class> classes = new ArrayList<>();
		for (Mark mark : marks) {
			classes.add(getClassByMark(mark));
		}
		return classes;
	}

	public ArrayList<Class> getClassesByTeacher(@NotNull Teacher teacher) {
		return getClassesByTid(teacher.getId());
	}

	public ArrayList<Class> getClassesByTeachers(@NotNull ArrayList<Teacher> teachers) {
		ArrayList<Class> classes = new ArrayList<>();
		for (Teacher teacher : teachers) {
			classes.addAll(getClassesByTeacher(teacher));
		}
		return classes;
	}

	public ArrayList<Class> getClassesByStudent(@NotNull Student student) {
		ArrayList<Class> classes = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getSID()))) {
			classes.add(getClassByDiary(diary));
		}
		return classes;
	}

	public ArrayList<Class> getClassesByStudents(@NotNull ArrayList<Student> students) {
		ArrayList<Class> classes = new ArrayList<>();
		for (Student student : students) {
			classes.addAll(getClassesByStudent(student));
		}
		return classes;
	}

	public ArrayList<Class> getClassesBySubject(@NotNull Subject subject) {
		ArrayList<Class> classes = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
			classes.add(getClassByMark(mark));
		}
		return classes;
	}

	public ArrayList<Class> getClassesBySubjects(@NotNull ArrayList<Subject> subjects) {
		ArrayList<Class> classes = new ArrayList<>();
		for (Subject subject : subjects) {
			classes.addAll(getClassesBySubject(subject));
		}
		return classes;
	}


}
