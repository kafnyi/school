package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentCrudService;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectCrudService;
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
	private final DiaryCrudService diaryCrudService1;
	private final ClassCrudService classCrudService;
	private final StudentCrudService studentCrudService;
	private List<Teacher> rContent;

	public TeacherService(DiaryCrudService diaryCrudService, MarkCrudService markCrudService, SubjectCrudService subjectCrudService, DiaryService diaryCrudService1, ClassService classCrudService, StudentService studentCrudService, TeacherRepository teacherRepository) {
		super(teacherRepository);
		this.diaryCrudService = diaryCrudService;
		this.markCrudService = markCrudService;
		this.subjectCrudService = subjectCrudService;
		this.diaryCrudService1 = diaryCrudService1;
		this.classCrudService = classCrudService;
		this.studentCrudService = studentCrudService;
	}


	List<Teacher> tWStudent(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case StudentId -> rContent = getTeachersByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
			case Name -> rContent = getTeachersByStudents(studentCrudService.getByName(value));
			case Date -> rContent = getTeachersByStudents(studentCrudService.getByBirth(value));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWDiary(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case DiaryId -> rContent = getTeachersByDiary(diaryCrudService1.getByDiaryId(Integer.parseInt(value)));
			case StudentId -> rContent = getTeachersByDiaries(diaryCrudService1.getByScid(Long.parseLong(value)));
			case ClassId -> rContent = getTeachersByDiaries(diaryCrudService1.getByClassid(Integer.parseInt(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWClass(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case ClassId -> rContent.add(getTeacherByClass(classCrudService.getByClassId(Integer.parseInt(value))));
			case Grade -> rContent = getTeachersByClasses(classCrudService.getByGrade(Short.parseShort(value)));
			case Sign -> rContent = getTeachersByClasses(classCrudService.getBySign(value.strip().charAt(0)));
			case Year -> rContent = getTeachersByClasses(classCrudService.getByYear(Year.parse(value)));
			case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWSubject(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case SubjectId ->
					rContent.add(getTeacherBySubject(subjectCrudService.getSubjectBySubjectId(Integer.parseInt(value))));
			case Name -> rContent = getTeacherBySubjects(subjectCrudService.getSubjectsByName(value));
			case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Teacher> tWMark(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case MarkId -> rContent.add(getTeacherByMark(markCrudService.getByMarkid(Long.parseLong(value))));
			case DiaryId -> rContent = getTeachersByMarks(markCrudService.getByDiaryid(Integer.parseInt(value)));
			case Date -> rContent = getTeachersByMarks(markCrudService.getByDate(value));
			case SubjectId ->
					rContent.add(getTeacherBySubject(subjectCrudService.getSubjectBySubjectId(Integer.parseInt(value))));
			case Mark -> rContent = getTeachersByMarks(markCrudService.getByMark(Byte.parseByte(value)));
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
		for (Mark mark : new ArrayList<>(markCrudService.getByDiaryid(diary.getId()))) {
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
		for (Diary diary : new ArrayList<>(diaryCrudService.getByScid(student.getId()))) {
			result.addAll(getTeachersByDiary(diary));
		}
		return result;
	}
}
