package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.mark.MarkService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectCrudService;
import hu.wurfel.refference.school.subject.SubjectService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService extends TeacherCrudService {
	private final DiaryCrudService diaryCrudService;
	private final MarkCrudService markCrudService;
	private final SubjectCrudService subjectCrudService;
	private final DiaryService diaryService;
	private final ClassService classService;
	private final MarkService markService;
	private final StudentService studentService;
	private final SubjectService subjectService;
	private List<Teacher> rContent;

	public TeacherService(DiaryCrudService diaryCrudService, MarkCrudService markCrudService, SubjectCrudService subjectCrudService, DiaryService diaryService, ClassService classService, MarkService markService, StudentService studentService, SubjectService subjectService, TeacherRepository teacherRepository) {
		super(teacherRepository);
		this.diaryCrudService = diaryCrudService;
		this.markCrudService = markCrudService;
		this.subjectCrudService = subjectCrudService;
		this.diaryService = diaryService;
		this.classService = classService;
		this.markService = markService;
		this.studentService = studentService;
		this.subjectService = subjectService;
	}


	List<Teacher> tWStudent(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case StudentId ->
					rContent = getTeachersByStudent(studentService.getStudentByStudentId(Long.parseLong(value)));
			case Name -> rContent = getTeachersByStudents(studentService.getStudentsByName(value));
			case Date -> rContent = getTeachersByStudents(studentService.getStudentsByBirth(value));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWDiary(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case DiaryId -> rContent = getTeachersByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(value)));
			case StudentId -> rContent = getTeachersByDiaries(diaryService.getDiariesByScid(Long.parseLong(value)));
			case ClassId -> rContent = getTeachersByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWClass(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case ClassId -> rContent.add(getTeacherByClass(classService.getClassByClassId(Integer.parseInt(value))));
			case Grade -> rContent = getTeachersByClasses(classService.getClassesByGrade(Short.parseShort(value)));
			case Sign -> rContent = getTeachersByClasses(classService.getClassesBySign(value.strip().charAt(0)));
			case Year -> rContent = getTeachersByClasses(classService.getClassesByYear(Year.parse(value)));
			case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWSubject(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case SubjectId ->
					rContent.add(getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(value))));
			case Name -> rContent = getTeacherBySubjects(subjectService.getSubjectsByName(value));
			case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWMark(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case MarkId -> rContent.add(getTeacherByMark(markService.getMarkByMarkid(Long.parseLong(value))));
			case DiaryId -> rContent = getTeachersByMarks(markService.getMarksByDiaryid(Integer.parseInt(value)));
			case Date -> rContent = getTeachersByMarks(markService.getMarksByDate(value));
			case SubjectId ->
					rContent.add(getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(value))));
			case Mark -> rContent = getTeachersByMarks(markService.getMarksByMark(Byte.parseByte(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWTeacher(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(value)));
			case Name -> rContent = getTeacherByName(value);
			case Date -> rContent = getTeacherByBirth(value);
			default -> rContent = null;
		}
		return rContent;
	}

	protected Teacher getTeacherByClass(@NotNull Class division) {
		return getTeacherByTeacherId(division.getTeacherId());
	}

	protected List<Teacher> getTeachersByClasses(@NotNull List<Class> classes) {
		List<Teacher> teachers = new ArrayList<>();
		for (Class division : classes) {
			teachers.add(getTeacherByClass(division));
		}
		return teachers;
	}

	protected Teacher getTeacherBySubject(@NotNull Subject subject) {
		return getTeacherByTeacherId(subject.getTid());
	}

	protected List<Teacher> getTeacherBySubjects(@NotNull List<Subject> subjects) {
		List<Teacher> teachers = new ArrayList<>();
		for (Subject subject : subjects) {
			teachers.add(getTeacherBySubject(subject));
		}
		return teachers;
	}

	protected Teacher getTeacherByMark(@NotNull Mark mark) {
		return getTeacherBySubject(subjectCrudService.getSubjectBySubjectId(mark.getSubjectID()));
	}

	protected List<Teacher> getTeachersByMarks(@NotNull List<Mark> marks) {
		List<Teacher> teachers = new ArrayList<>();
		for (Mark mark : marks) {
			teachers.add(getTeacherByMark(mark));
		}
		return teachers;
	}

	protected List<Teacher> getTeachersByDiary(@NotNull Diary diary) {
		List<Teacher> result = new ArrayList<>();
		for (Mark mark : new ArrayList<>(markCrudService.getMarksByDiaryid(diary.getId()))) {
			result.add(getTeacherByMark(mark));
		}
		return result;
	}

	protected List<Teacher> getTeachersByDiaries(@NotNull List<Diary> diaries) {
		List<Teacher> teachers = new ArrayList<>();
		for (Diary diary : diaries) {
			teachers.addAll(getTeachersByDiary(diary));
		}
		return teachers;
	}

	protected List<Teacher> getTeachersByStudents(@NotNull List<Student> students) {
		List<Teacher> teachers = new ArrayList<>();
		for (Student student : students) {
			teachers.addAll(getTeachersByStudent(student));
		}
		return teachers;
	}

	protected List<Teacher> getTeachersByStudent(@NotNull Student student) {
		List<Teacher> result = new ArrayList<>();
		for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByScid(student.getId()))) {
			result.addAll(getTeachersByDiary(diary));
		}
		return result;
	}
}
