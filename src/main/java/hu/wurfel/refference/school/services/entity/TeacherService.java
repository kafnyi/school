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

	public ArrayList<Teacher> getTeachersByClasses(@NotNull ArrayList<Class> classes) {
		ArrayList<Teacher> teachers = new ArrayList<>();
		for (Class division : classes) {
			teachers.add(getTeacherByClass(division));
		}
		return teachers;
	}

	public Teacher getTeacherBySubject(@NotNull Subject subject) {
		return getTeacherByTeacherId(subject.getTid());
	}

	public ArrayList<Teacher> getTeacherBySubjects(@NotNull ArrayList<Subject> subjects) {
		ArrayList<Teacher> teachers = new ArrayList<>();
		for (Subject subject : subjects) {
			teachers.add(getTeacherBySubject(subject));
		}
		return teachers;
	}

	public Teacher getTeacherByMark(@NotNull Mark mark) {
		return getTeacherBySubject(subjectCrudService.getSubjectBySubjectId(mark.getSubjectID()));
	}

	public ArrayList<Teacher> getTeachersByMarks(@NotNull ArrayList<Mark> marks) {
		ArrayList<Teacher> teachers = new ArrayList<>();
		for (Mark mark : marks) {
			teachers.add(getTeacherByMark(mark));
		}
		return teachers;
	}

	public ArrayList<Teacher> getTeachersByDiary(@NotNull Diary diary) {
		ArrayList<Teacher> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksByDiaryid(diary.getId()))) {
			result.add(getTeacherByMark(mark));
		}
		return result;
	}

	public ArrayList<Teacher> getTeachersByDiaries(@NotNull ArrayList<Diary> diaries) {
		ArrayList<Teacher> teachers = new ArrayList<>();
		for (Diary diary : diaries) {
			teachers.addAll(getTeachersByDiary(diary));
		}
		return teachers;
	}

	public ArrayList<Teacher> getTeachersByStudents(@NotNull ArrayList<Student> students) {
		ArrayList<Teacher> teachers = new ArrayList<>();
		for (Student student : students) {
			teachers.addAll(getTeachersByStudent(student));
		}
		return teachers;
	}

	public ArrayList<Teacher> getTeachersByStudent(@NotNull Student student) {
		ArrayList<Teacher> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getSID()))) {
			result.addAll(getTeachersByDiary(diary));
		}
		return result;
	}
}
