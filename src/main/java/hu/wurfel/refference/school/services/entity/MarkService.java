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
public class MarkService extends MarkCrudService {
	@Autowired
	private DiaryCrudService diaryCrudService;
	@Autowired
	private SubjectCrudService subjectCrudService;

	public ArrayList<Mark> getMarksByDiary(@NotNull Diary diary) {
		return getMarksByDiaryid(diary.getId());
	}

	public ArrayList<Mark> getMarksByDiaries(@NotNull ArrayList<Diary> diaries) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Diary diary : diaries) {
			marks.addAll(getMarksByDiary(diary));
		}
		return marks;
	}

	public ArrayList<Mark> getMarksByStudent(@NotNull Student student) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getId()))) {
			marks.addAll(getMarksByDiary(diary));
		}
		return marks;
	}

	public ArrayList<Mark> getMarksByStudents(@NotNull ArrayList<Student> students) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Student student : students) {
			marks.addAll(getMarksByStudent(student));
		}
		return marks;
	}

	public ArrayList<Mark> getMarksByClass(@NotNull Class division) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByClassid(division.getId()))) {
			marks.addAll(getMarksByDiary(diary));
		}
		return marks;
	}

	public ArrayList<Mark> getMarksByClasses(@NotNull ArrayList<Class> classes) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Class division : classes) {
			marks.addAll(getMarksByClass(division));
		}
		return marks;
	}

	public ArrayList<Mark> getMarksBySubject(@NotNull Subject subject) {
		return getMarksBySubjectid(subject.getId());
	}

	public ArrayList<Mark> getMarksBySubjects(@NotNull ArrayList<Subject> subjects) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Subject subject : subjects) {
			marks.addAll(getMarksBySubject(subject));
		}
		return marks;
	}

	public ArrayList<Mark> getMarksByTeacher(@NotNull Teacher teacher) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Subject subject : new ArrayList<Subject>(subjectCrudService.getSubjectsByTid(teacher.getId()))) {
			marks.addAll(getMarksBySubject(subject));
		}
		return marks;
	}

	public ArrayList<Mark> getMarksByTeachers(@NotNull ArrayList<Teacher> teachers) {
		ArrayList<Mark> marks = new ArrayList<>();
		for (Teacher teacher : teachers) {
			marks.addAll(getMarksByTeacher(teacher));
		}
		return marks;
	}
}
