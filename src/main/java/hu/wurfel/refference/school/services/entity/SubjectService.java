package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.DiaryCrudService;
import hu.wurfel.refference.school.services.entity.cruds.MarkCrudService;
import hu.wurfel.refference.school.services.entity.cruds.SubjectCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService extends SubjectCrudService {
	@Autowired
	private DiaryCrudService diaryCrudService;
	@Autowired
	private MarkCrudService markCrudService;

	public Subject getSubjectByMark(@NotNull Mark mark) {
		return getSubjectBySubjectId(mark.getSubjectID());
	}

	public ArrayList<Subject> getSubjectsByMarks(@NotNull ArrayList<Mark> marks) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Mark mark : marks) {
			subjects.add(getSubjectByMark(mark));
		}
		return subjects;
	}

	public ArrayList<Subject> getSubjectsByTeacher(@NotNull Teacher teacher) {
		return getSubjectsByTid(teacher.getId());
	}

	public ArrayList<Subject> getSubjectsByTeachers(@NotNull ArrayList<Teacher> teachers) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Teacher teacher : teachers) {
			subjects.addAll(getSubjectsByTeacher(teacher));
		}
		return subjects;
	}

	public ArrayList<Subject> getSubjectsByDiary(@NotNull Diary diary) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksByDiaryid(diary.getId()))) {
			subjects.add(getSubjectByMark(mark));
		}
		return subjects;
	}

	public ArrayList<Subject> getSubjectsByDiaries(@NotNull ArrayList<Diary> diaries) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Diary diary : diaries) {
			subjects.addAll(getSubjectsByDiary(diary));
		}
		return subjects;
	}

	public ArrayList<Subject> getSubjectsByStudent(@NotNull Student student) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getId()))) {
			subjects.addAll(getSubjectsByDiary(diary));
		}
		return subjects;
	}

	public ArrayList<Subject> getSubjectsByStudents(@NotNull ArrayList<Student> students) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Student student : students) {
			subjects.addAll(getSubjectsByStudent(student));
		}
		return subjects;
	}


	public ArrayList<Subject> getSubjectsByClass(@NotNull Class division) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByClassid(division.getId()))) {
			subjects.addAll(getSubjectsByDiary(diary));
		}
		return subjects;
	}

	public ArrayList<Subject> getSubjectsByClasses(@NotNull ArrayList<Class> classes) {
		ArrayList<Subject> subjects = new ArrayList<>();
		for (Class division : classes) {
			subjects.addAll(getSubjectsByClass(division));
		}
		return subjects;
	}

}
