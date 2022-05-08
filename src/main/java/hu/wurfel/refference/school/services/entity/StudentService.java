package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.*;
import hu.wurfel.refference.school.services.entity.cruds.StudentCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService extends StudentCrudService {
	@Autowired
	private ClassService cs;
	@Autowired
	private DiaryService ds;
	@Autowired
	private MarkService ms;

	public Student getStudentByDiary(@NotNull Diary diary) {
		return getStudentByStudentId(diary.getScid());
	}

	public Student getStudentByMark(@NotNull Mark mark) {
		return getStudentByStudentId(ds.getDiaryByDiaryid(mark.getDiaryID()).getId());
	}

	public ArrayList<Student> getStudentsByClass(@NotNull Class division) {
		ArrayList<Student> result = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(ds.getDiariesByClassid(division.getId()))) {
			result.add(getStudentByDiary(diary));
		}
		return result;
	}

	public ArrayList<Student> getStudentsBySubject(@NotNull Subject subject) {
		ArrayList<Student> result = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(ms.getMarksBySubjectid(subject.getId()))) {
			result.add(getStudentByMark(mark));
		}
		return result;
	}

	public ArrayList<Student> getStudentsByTeacher(@NotNull Teacher teacher) {
		ArrayList<Student> result = new ArrayList<>();
		for (Class division : new ArrayList<Class>(cs.getClassesByTid(teacher.getId()))) {
			result.addAll(getStudentsByClass(division));
		}
		return result;
	}


}
