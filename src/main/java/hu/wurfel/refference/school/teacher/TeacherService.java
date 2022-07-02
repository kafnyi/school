package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
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

	List<Teacher> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

		switch (searchWith) {
			case Student -> {
				return findByStudent(searchBy, value);
			}
			case Diary -> {
				return findByDiary(searchBy, value);
			}
			case Class -> {
				return findByClass(searchBy, value);
			}
			case Subject -> {
				return findBySubject(searchBy, value);
			}
			case Mark -> {
				return findByMark(searchBy, value);
			}
			case Teacher -> {
				return findByTeacher(searchBy, value);
			}
			default -> {
				return new ArrayList<>();
			}
		}
	}

	List<Teacher> findByStudent(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
			case Name -> rContent = getByStudents(studentCrudService.getByName(value));
			case Date -> rContent = getByStudents(studentCrudService.getByBirth(value));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Teacher> findByDiary(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case DiaryId -> rContent = getByDiary(diaryCrudService1.getByDiaryId(Integer.parseInt(value)));
			case StudentId -> rContent = getByDiaries(diaryCrudService1.getAllByStudentId(Long.parseLong(value)));
			case ClassId -> rContent = getByDiaries(diaryCrudService1.getAllByClassId(Integer.parseInt(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Teacher> findByClass(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case ClassId -> rContent.add(getByClass(classCrudService.getByClassId(Integer.parseInt(value))));
			case Grade -> rContent = getByClasses(classCrudService.getAllByGrade(Short.parseShort(value)));
			case Sign -> rContent = getByClasses(classCrudService.getAllBySign(value.strip().charAt(0)));
			case Year -> rContent = getByClasses(classCrudService.getAllByYear(Year.parse(value)));
			case TeacherId -> rContent.add(getByTeacherId(Long.parseLong(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Teacher> findBySubject(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case SubjectId -> rContent.add(getBySubject(subjectCrudService.getBySubjectId(Integer.parseInt(value))));
			case Name -> rContent = getBySubjects(subjectCrudService.getByName(value));
			case TeacherId -> rContent.add(getByTeacherId(Long.parseLong(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Teacher> findByMark(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case MarkId -> rContent.add(getByMark(markCrudService.getByMarkId(Long.parseLong(value))));
			case DiaryId -> rContent = getByMarks(markCrudService.getAllByDiaryId(Integer.parseInt(value)));
			case Date -> rContent = getByMarks(markCrudService.getAllByDate(value));
			case SubjectId -> rContent.add(getBySubject(subjectCrudService.getBySubjectId(Integer.parseInt(value))));
			case Mark -> rContent = getByMarks(markCrudService.getAllByMark(Byte.parseByte(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Teacher> findByTeacher(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case TeacherId -> rContent.add(getByTeacherId(Long.parseLong(value)));
			case Name -> rContent = getByName(value);
			case Date -> rContent = getByBirth(value);
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	protected Teacher getByClass(@NotNull Class division) {
		return getByTeacherId(division.getTeacherId());
	}

	protected List<Teacher> getByClasses(@NotNull List<Class> classes) {
		List<Teacher> teachers = new ArrayList<>();
		for (Class division : classes) {
			teachers.add(getByClass(division));
		}
		return teachers;
	}

	protected Teacher getBySubject(@NotNull Subject subject) {
		return getByTeacherId(subject.getTid());
	}

	protected List<Teacher> getBySubjects(@NotNull List<Subject> subjects) {
		List<Teacher> teachers = new ArrayList<>();
		for (Subject subject : subjects) {
			teachers.add(getBySubject(subject));
		}
		return teachers;
	}

	protected Teacher getByMark(@NotNull Mark mark) {
		return getBySubject(subjectCrudService.getBySubjectId(mark.getSubjectID()));
	}

	protected List<Teacher> getByMarks(@NotNull List<Mark> marks) {
		List<Teacher> teachers = new ArrayList<>();
		for (Mark mark : marks) {
			teachers.add(getByMark(mark));
		}
		return teachers;
	}

	protected List<Teacher> getByDiary(@NotNull Diary diary) {
		List<Teacher> result = new ArrayList<>();
		for (Mark mark : new ArrayList<>(markCrudService.getAllByDiaryId(diary.getId()))) {
			result.add(getByMark(mark));
		}
		return result;
	}

	protected List<Teacher> getByDiaries(@NotNull List<Diary> diaries) {
		List<Teacher> teachers = new ArrayList<>();
		for (Diary diary : diaries) {
			teachers.addAll(getByDiary(diary));
		}
		return teachers;
	}

	protected List<Teacher> getByStudents(@NotNull List<Student> students) {
		List<Teacher> teachers = new ArrayList<>();
		for (Student student : students) {
			teachers.addAll(getByStudent(student));
		}
		return teachers;
	}

	protected List<Teacher> getByStudent(@NotNull Student student) {
		List<Teacher> result = new ArrayList<>();
		for (Diary diary : new ArrayList<>(diaryCrudService.getAllByStudentId(student.getId()))) {
			result.addAll(getByDiary(diary));
		}
		return result;
	}
}
