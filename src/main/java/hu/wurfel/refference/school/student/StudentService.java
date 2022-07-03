package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
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


	List<StudentDto> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

		switch (searchWith) {
			case Student -> {
				return getDtoList(findByStudent(searchBy, value));
			}
			case Diary -> {
				return getDtoList(findByDiary(searchBy, value));
			}
			case Class -> {
				return getDtoList(findByClass(searchBy, value));
			}
			case Subject -> {
				return getDtoList(findBySubject(searchBy, value));
			}
			case Mark -> {
				return getDtoList(findByMark(searchBy, value));
			}
			case Teacher -> {
				return getDtoList(findByTeacher(searchBy, value));
			}
			default -> {
				return new ArrayList<>();
			}
		}
	}

	List<Student> findByStudent(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case StudentId -> rContent.add(getById(Long.parseLong(value)));
			case Name -> rContent = getAllByName(value);
			case Date -> rContent = getAllByBirth(value);
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Student> findByDiary(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case DiaryId -> rContent.add(getByDiary(diaryCrudService.getById(Integer.parseInt(value))));
			case StudentId -> rContent.add(getById(Long.parseLong(value)));
			case ClassId -> rContent = getByDiaries(diaryCrudService.getAllByClassId(Integer.parseInt(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Student> findByClass(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case ClassId -> rContent = getByClass(classCrudService.getById(Integer.parseInt(value)));
			case Grade -> rContent = getByClasses(classCrudService.getAllByGrade(Short.parseShort(value)));
			case Sign -> rContent = getByClasses(classCrudService.getAllBySign(value.strip().charAt(0)));
			case Year -> rContent = getByClasses(classCrudService.getAllByYear(Year.parse(value)));
			case TeacherId -> rContent = getByClasses(classCrudService.getAllByTeacherId(Long.parseLong(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Student> findBySubject(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case SubjectId -> rContent = getBySubject(subjectCrudService.getById(Integer.parseInt(value)));
			case Name -> rContent = getBySubjects(subjectCrudService.getAllByName(value));
			case TeacherId -> rContent = getBySubjects(subjectCrudService.getAllByTeacherId(Long.parseLong(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Student> findByMark(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case MarkId -> rContent.add(getByMark(markCrudService.getById(Long.parseLong(value))));
			case DiaryId -> rContent.add(getByDiary(diaryCrudService.getById(Integer.parseInt(value))));
			case Date -> rContent = getByMarks(markCrudService.getAllByDate(value));
			case SubjectId -> rContent = getBySubject(subjectCrudService.getById(Integer.parseInt(value)));
			case Mark -> rContent = getByMarks(markCrudService.getAllByMark(Byte.parseByte(value)));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	List<Student> findByTeacher(EntityFieldNames searchBy, String value) {
		switch (searchBy) {
			case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
			case Name -> rContent = getByTeachers(teacherCrudService.getAllByName(value));
			case Date -> rContent = getByTeachers(teacherCrudService.getAllByBirth(value));
			default -> rContent = new ArrayList<>();
		}
		return rContent;
	}

	protected List<StudentDto> modify(StudentDto studentDto) {
		Student student = getById(studentDto.getId());
		student.setName(studentDto.getName());
		student.setBirthDate(studentDto.getBirthDate());
		List<StudentDto> answer = new ArrayList<>();
		answer.add(getDto(save(student)));
		return answer;
	}

	protected StudentDto getDto(@NotNull Student student) {
		return new StudentDto(student.getId(), student.getName(), student.getBirthDate());
	}

	protected List<StudentDto> getDtoList(List<Student> studentList) {
		ArrayList<StudentDto> dtoList = new ArrayList<>();
		for (Student student : studentList) {
			dtoList.add(getDto(student));
		}
		return dtoList;
	}

	protected Student getByDiary(@NotNull Diary diary) {
		return getById(diary.getStudentId());
	}

	protected List<Student> getByDiaries(@NotNull List<Diary> diaries) {
		List<Student> students = new ArrayList<>();
		for (Diary diary : diaries) {
			students.add(getByDiary(diary));
		}
		return students;
	}

	protected Student getByMark(@NotNull Mark mark) {
		return getById(diaryCrudService.getById(mark.getDiaryId()).getId());
	}

	protected List<Student> getByMarks(@NotNull List<Mark> marks) {
		List<Student> students = new ArrayList<>();
		for (Mark mark : marks) {
			students.add(getByMark(mark));
		}
		return students;
	}

	protected List<Student> getByClass(@NotNull Class division) {
		List<Student> students = new ArrayList<>();
		for (Diary diary : new ArrayList<>(diaryCrudService.getAllByClassId(division.getId()))) {
			students.add(getByDiary(diary));
		}
		return students;
	}

	protected List<Student> getByClasses(@NotNull List<Class> classes) {
		List<Student> students = new ArrayList<>();
		for (Class division : classes) {
			students.addAll(getByClass(division));
		}
		return students;
	}

	protected List<Student> getBySubject(@NotNull Subject subject) {
		List<Student> students = new ArrayList<>();
		for (Mark mark : new ArrayList<>(markCrudService.getAllBySubjectId(subject.getId()))) {
			students.add(getByMark(mark));
		}
		return students;
	}

	protected List<Student> getBySubjects(@NotNull List<Subject> subjects) {
		List<Student> students = new ArrayList<>();
		for (Subject subject : subjects) {
			students.addAll(getBySubject(subject));
		}
		return students;
	}

	protected List<Student> getByTeacher(@NotNull Teacher teacher) {
		List<Student> students = new ArrayList<>();
		for (Class division : new ArrayList<>(classCrudService.getAllByTeacherId(teacher.getId()))) {
			students.addAll(getByClass(division));
		}
		return students;
	}

	protected List<Student> getByTeachers(@NotNull List<Teacher> teachers) {
		List<Student> students = new ArrayList<>();
		for (Teacher teacher : teachers) {
			students.addAll(getByTeacher(teacher));
		}
		return students;
	}
}