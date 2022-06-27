package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectCrudService;
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherCrudService;
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
	private final SubjectCrudService subjectCrudService;
	private final TeacherCrudService teacherCrudService;
	private List<Student> rContent;

	public StudentService(StudentRepository studentRepository, ClassCrudService classCrudService, DiaryCrudService diaryCrudService, MarkCrudService markCrudService, SubjectCrudService subjectCrudService, TeacherCrudService teacherCrudService) {
		super(studentRepository);
		this.classCrudService = classCrudService;
		this.diaryCrudService = diaryCrudService;
		this.markCrudService = markCrudService;
		this.subjectCrudService = subjectCrudService;
		this.teacherCrudService = teacherCrudService;
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
			case DiaryId ->
					rContent.add(getStudentByDiary(diaryCrudService.getByDiaryid(Integer.parseInt(value))));
			case StudentId -> rContent.add(getStudentByStudentId(Long.parseLong(value)));
			case ClassId -> rContent = getStudentsByDiaries(diaryCrudService.getByClassid(Integer.parseInt(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWClass(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case ClassId -> rContent = getStudentsByClass(classCrudService.getClassByClassId(Integer.parseInt(value)));
			case Grade -> rContent = getStudentsByClasses(classCrudService.getClassesByGrade(Short.parseShort(value)));
			case Sign -> rContent = getStudentsByClasses(classCrudService.getClassesBySign(value.strip().charAt(0)));
			case Year -> rContent = getStudentsByClasses(classCrudService.getClassesByYear(Year.parse(value)));
			case TeacherId -> rContent = getStudentsByClasses(classCrudService.getClassesByTid(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWSubject(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case SubjectId ->
					rContent = getStudentsBySubject(subjectCrudService.getSubjectBySubjectId(Integer.parseInt(value)));
			case Name -> rContent = getStudentsBySubjects(subjectCrudService.getSubjectsByName(value));
			case TeacherId ->
					rContent = getStudentsBySubjects(subjectCrudService.getSubjectsByTid(Long.parseLong(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWMark(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case MarkId -> rContent.add(getStudentByMark(markCrudService.getMarkByMarkid(Long.parseLong(value))));
			case DiaryId -> rContent.add(getStudentByDiary(diaryCrudService.getByDiaryid(Integer.parseInt(value))));
			case Date -> rContent = getStudentsByMarks(markCrudService.getMarksByDate(value));
			case SubjectId ->
					rContent = getStudentsBySubject(subjectCrudService.getSubjectBySubjectId(Integer.parseInt(value)));
			case Mark -> rContent = getStudentsByMarks(markCrudService.getMarksByMark(Byte.parseByte(value)));
			default -> rContent = null;
		}
		return rContent;
	}

	List<Student> sWTeacher(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case TeacherId ->
					rContent = getStudentsByTeacher(teacherCrudService.getTeacherByTeacherId(Long.parseLong(value)));
			case Name -> rContent = getStudentsByTeachers(teacherCrudService.getTeacherByName(value));
			case Date -> rContent = getStudentsByTeachers(teacherCrudService.getTeacherByBirth(value));
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
		return getStudentByStudentId(diaryCrudService.getByDiaryid(mark.getDiaryID()).getId());
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
		for (Diary diary : new ArrayList<>(diaryCrudService.getByClassid(division.getId()))) {
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