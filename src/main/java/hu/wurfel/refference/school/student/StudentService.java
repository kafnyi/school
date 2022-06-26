package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.mark.MarkService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectService;
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class StudentService extends StudentCrudService {

	private ClassCrudService classCrudService;
	private DiaryCrudService diaryCrudService;
	private MarkCrudService markCrudService;
	private DiaryService diaryService;
	private ClassService classService;
	private MarkService markService;
	private SubjectService subjectService;
	private TeacherService teacherService;
	private ArrayList<Student> rContent;

	public ArrayList getAutomated(StudentRequestForSearch request) {
		rContent = new ArrayList<>();
		switch (request.getSearchWith()) {
			case Student -> sWStudent(request);
			case Diary -> sWDiary(request);
			case Class -> sWClass(request);
			case Subject -> sWSubject(request);
			case Mark -> sWMark(request);
			case Teacher -> sWTeacher(request);
			default -> {
			}
		}
		return rContent;
	}

	public void sWStudent(StudentRequestForSearch request) {
		switch (request.getSearchBy()) {
			case StudentId -> rContent.add(getStudentByStudentId(Long.parseLong(request.getSearchValue())));
			case Name -> rContent = getStudentsByName(request.getSearchValue());
			case Date -> rContent = getStudentsByBirth(request.getSearchValue());
			default -> {
			}
		}
	}

	private void sWDiary(StudentRequestForSearch request) {
		switch (request.getSearchBy()) {
			case DiaryId ->
					rContent.add(getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getSearchValue()))));
			case StudentId -> rContent.add(getStudentByStudentId(Long.parseLong(request.getSearchValue())));
			case ClassId ->
					rContent = getStudentsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getSearchValue())));
			default -> {
			}
		}
	}

	private void sWClass(StudentRequestForSearch request) {
		switch (request.getSearchBy()) {
			case ClassId ->
					rContent = getStudentsByClass(classService.getClassByClassId(Integer.parseInt(request.getSearchValue())));
			case Grade ->
					rContent = getStudentsByClasses(classService.getClassesByGrade(Short.parseShort(request.getSearchValue())));
			case Sign ->
					rContent = getStudentsByClasses(classService.getClassesBySign(request.getSearchValue().strip().charAt(0)));
			case Year ->
					rContent = getStudentsByClasses(classService.getClassesByYear(Year.parse(request.getSearchValue())));
			case TeacherId ->
					rContent = getStudentsByClasses(classService.getClassesByTid(Long.parseLong(request.getSearchValue())));
			default -> {
			}
		}
	}

	private void sWSubject(StudentRequestForSearch request) {
		switch (request.getSearchBy()) {
			case SubjectId ->
					rContent = getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
			case Name -> rContent = getStudentsBySubjects(subjectService.getSubjectsByName(request.getSearchValue()));
			case TeacherId ->
					rContent = getStudentsBySubjects(subjectService.getSubjectsByTid(Long.parseLong(request.getSearchValue())));
			default -> {
			}
		}
	}

	private void sWMark(StudentRequestForSearch request) {
		switch (request.getSearchBy()) {
			case MarkId ->
					rContent.add(getStudentByMark(markService.getMarkByMarkid(Long.parseLong(request.getSearchValue()))));
			case DiaryId ->
					rContent.add(getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getSearchValue()))));
			case Date -> rContent = getStudentsByMarks(markService.getMarksByDate(request.getSearchValue()));
			case SubjectId ->
					rContent = getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
			case Mark ->
					rContent = getStudentsByMarks(markService.getMarksByMark(Byte.parseByte(request.getSearchValue())));
			default -> {
			}
		}
	}

	private void sWTeacher(StudentRequestForSearch request) {
		switch (request.getSearchBy()) {
			case TeacherId ->
					rContent = getStudentsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getSearchValue())));
			case Name -> rContent = getStudentsByTeachers(teacherService.getTeacherByName(request.getSearchValue()));
			case Date -> rContent = getStudentsByTeachers(teacherService.getTeacherByBirth(request.getSearchValue()));
			default -> {
			}
		}
	}

	protected Student getStudentByDiary(@NotNull Diary diary) {
		return getStudentByStudentId(diary.getStudentId());
	}

	protected ArrayList<Student> getStudentsByDiaries(@NotNull ArrayList<Diary> diaries) {
		ArrayList<Student> students = new ArrayList<Student>();
		for (Diary diary : diaries) {
			students.add(getStudentByDiary(diary));
		}
		return students;
	}

	protected Student getStudentByMark(@NotNull Mark mark) {
		return getStudentByStudentId(diaryCrudService.getDiaryByDiaryid(mark.getDiaryID()).getId());
	}

	protected ArrayList<Student> getStudentsByMarks(@NotNull ArrayList<Mark> marks) {
		ArrayList<Student> students = new ArrayList<>();
		for (Mark mark : marks) {
			students.add(getStudentByMark(mark));
		}
		return students;
	}

	protected ArrayList<Student> getStudentsByClass(@NotNull Class division) {
		ArrayList<Student> students = new ArrayList<>();
		for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByClassid(division.getId()))) {
			students.add(getStudentByDiary(diary));
		}
		return students;
	}

	protected ArrayList<Student> getStudentsByClasses(@NotNull ArrayList<Class> classes) {
		ArrayList<Student> students = new ArrayList<>();
		for (Class division : classes) {
			students.addAll(getStudentsByClass(division));
		}
		return students;
	}

	protected ArrayList<Student> getStudentsBySubject(@NotNull Subject subject) {
		ArrayList<Student> students = new ArrayList<>();
		for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
			students.add(getStudentByMark(mark));
		}
		return students;
	}

	protected ArrayList<Student> getStudentsBySubjects(@NotNull ArrayList<Subject> subjects) {
		ArrayList<Student> students = new ArrayList<>();
		for (Subject subject : subjects) {
			students.addAll(getStudentsBySubject(subject));
		}
		return students;
	}

	protected ArrayList<Student> getStudentsByTeacher(@NotNull Teacher teacher) {
		ArrayList<Student> students = new ArrayList<>();
		for (Class division : new ArrayList<Class>(classCrudService.getClassesByTid(teacher.getId()))) {
			students.addAll(getStudentsByClass(division));
		}
		return students;
	}

	protected ArrayList<Student> getStudentsByTeachers(@NotNull ArrayList<Teacher> teachers) {
		ArrayList<Student> students = new ArrayList<>();
		for (Teacher teacher : teachers) {
			students.addAll(getStudentsByTeacher(teacher));
		}
		return students;
	}
}