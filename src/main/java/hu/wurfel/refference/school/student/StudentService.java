package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
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
import java.util.List;

@Service
public class StudentService extends StudentCrudService {

	private final ClassCrudService classCrudService;
	private final DiaryCrudService diaryCrudService;
	private final MarkCrudService markCrudService;
	private final DiaryService diaryService;
	private final ClassService classService;
	private final MarkService markService;
	private final SubjectService subjectService;
	private final TeacherService teacherService;
	private List<Student> rContent;


	public StudentService(StudentRepository studentRepository, ClassCrudService classCrudService, DiaryCrudService diaryCrudService, MarkCrudService markCrudService, DiaryService diaryService, ClassService classService, MarkService markService, SubjectService subjectService, TeacherService teacherService) {
		super(studentRepository);
		this.classCrudService = classCrudService;
		this.diaryCrudService = diaryCrudService;
		this.markCrudService = markCrudService;
		this.diaryService = diaryService;
		this.classService = classService;
		this.markService = markService;
		this.subjectService = subjectService;
		this.teacherService = teacherService;
	}

	List<Student> sWStudent(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case StudentId -> rContent.add(getStudentByStudentId(Long.parseLong(value)));
			case Name -> rContent = getStudentsByName(value);
			case Date -> rContent = getStudentsByBirth(value);
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWDiary(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case DiaryId -> rContent.add(getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(value))));
			case StudentId -> rContent.add(getStudentByStudentId(Long.parseLong(value)));
			case ClassId -> rContent = getStudentsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWClass(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case ClassId -> rContent = getStudentsByClass(classService.getClassByClassId(Integer.parseInt(value)));
			case Grade -> rContent = getStudentsByClasses(classService.getClassesByGrade(Short.parseShort(value)));
			case Sign -> rContent = getStudentsByClasses(classService.getClassesBySign(value.strip().charAt(0)));
			case Year -> rContent = getStudentsByClasses(classService.getClassesByYear(Year.parse(value)));
			case TeacherId -> rContent = getStudentsByClasses(classService.getClassesByTid(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWSubject(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case SubjectId ->
					rContent = getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(value)));
			case Name -> rContent = getStudentsBySubjects(subjectService.getSubjectsByName(value));
			case TeacherId -> rContent = getStudentsBySubjects(subjectService.getSubjectsByTid(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWMark(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case MarkId -> rContent.add(getStudentByMark(markService.getMarkByMarkid(Long.parseLong(value))));
			case DiaryId -> rContent.add(getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(value))));
			case Date -> rContent = getStudentsByMarks(markService.getMarksByDate(value));
			case SubjectId ->
					rContent = getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(value)));
			case Mark -> rContent = getStudentsByMarks(markService.getMarksByMark(Byte.parseByte(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWTeacher(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case TeacherId ->
					rContent = getStudentsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(value)));
			case Name -> rContent = getStudentsByTeachers(teacherService.getTeacherByName(value));
			case Date -> rContent = getStudentsByTeachers(teacherService.getTeacherByBirth(value));
			default -> rContent = null;
		}
		return rContent;
	}

	protected Student getStudentByDiary(@NotNull Diary diary) {
		return getStudentByStudentId(diary.getStudentId());
	}

	protected List<Student> getStudentsByDiaries(@NotNull List<Diary> diaries) {
		List<Student> students = new ArrayList<>();
		for (Diary diary : diaries) {
			students.add(getStudentByDiary(diary));
		}
		return students;
	}

	protected Student getStudentByMark(@NotNull Mark mark) {
		return getStudentByStudentId(diaryCrudService.getDiaryByDiaryid(mark.getDiaryID()).getId());
	}

	protected List<Student> getStudentsByMarks(@NotNull List<Mark> marks) {
		List<Student> students = new ArrayList<>();
		for (Mark mark : marks) {
			students.add(getStudentByMark(mark));
		}
		return students;
	}

	protected List<Student> getStudentsByClass(@NotNull Class division) {
		List<Student> students = new ArrayList<>();
		for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByClassid(division.getId()))) {
			students.add(getStudentByDiary(diary));
		}
		return students;
	}

	protected List<Student> getStudentsByClasses(@NotNull List<Class> classes) {
		List<Student> students = new ArrayList<>();
		for (Class division : classes) {
			students.addAll(getStudentsByClass(division));
		}
		return students;
	}

	protected List<Student> getStudentsBySubject(@NotNull Subject subject) {
		List<Student> students = new ArrayList<>();
		for (Mark mark : new ArrayList<>(markCrudService.getMarksBySubjectid(subject.getId()))) {
			students.add(getStudentByMark(mark));
		}
		return students;
	}

	protected List<Student> getStudentsBySubjects(@NotNull List<Subject> subjects) {
		List<Student> students = new ArrayList<>();
		for (Subject subject : subjects) {
			students.addAll(getStudentsBySubject(subject));
		}
		return students;
	}

	protected List<Student> getStudentsByTeacher(@NotNull Teacher teacher) {
		List<Student> students = new ArrayList<>();
		for (Class division : new ArrayList<>(classCrudService.getClassesByTid(teacher.getId()))) {
			students.addAll(getStudentsByClass(division));
		}
		return students;
	}

	protected List<Student> getStudentsByTeachers(@NotNull List<Teacher> teachers) {
		List<Student> students = new ArrayList<>();
		for (Teacher teacher : teachers) {
			students.addAll(getStudentsByTeacher(teacher));
		}
		return students;
	}
}