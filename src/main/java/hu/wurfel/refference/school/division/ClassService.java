package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentCrudService;
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
public class ClassService extends ClassCrudService {

    private final MarkCrudService markCrudService;
    private final DiaryCrudService diaryCrudService;
    private final StudentCrudService studentCrudService;
    private final SubjectCrudService subjectCrudService;
    private final TeacherCrudService teacherCrudService;
    private List<Class> rContent;

    public ClassService(ClassRepository classRepository, MarkCrudService markCrudService, DiaryCrudService diaryCrudService, StudentCrudService studentCrudService, SubjectCrudService subjectCrudService, TeacherCrudService teacherCrudService) {
        super(classRepository);
        this.markCrudService = markCrudService;
        this.diaryCrudService = diaryCrudService;
        this.studentCrudService = studentCrudService;
        this.subjectCrudService = subjectCrudService;
        this.teacherCrudService = teacherCrudService;
    }

    List<ClassDto> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

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

    List<Class> findByStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
            case Name -> rContent = getByStudents(studentCrudService.getAllByName(value));
            case Date -> rContent = getByStudents(studentCrudService.getAllByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Class> findByDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent.add(getByDiary(diaryCrudService.getById(Integer.parseInt(value))));
            case StudentId -> rContent = getClassesByDiaries(diaryCrudService.getAllByStudentId(Long.parseLong(value)));
            case ClassId -> rContent.add(getByClassId(Integer.parseInt(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Class> findByClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent.add(getByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getAllByGrade(Short.parseShort(value));
            case Sign -> rContent = getAllBySign(value.strip().charAt(0));
            case Year -> rContent = getAllByYear(Year.parse(value));
            case TeacherId -> rContent = getAllByTeacherId(Long.parseLong(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Class> findBySubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent = getBySubject(subjectCrudService.getBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getBySubjects(subjectCrudService.getAllByName(value));
            case TeacherId -> rContent = getBySubjects(subjectCrudService.getAllByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Class> findByMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getByMark(markCrudService.getByMarkId(Long.parseLong(value))));
            case DiaryId -> rContent.add(getByDiary(diaryCrudService.getById(Integer.parseInt(value))));
            case Date -> rContent = getClassesByMarks(markCrudService.getAllByDate(value));
            case SubjectId -> rContent = getClassesByMarks(markCrudService.getAllBySubjectId(Integer.parseInt(value)));
            case Mark -> rContent = getClassesByMarks(markCrudService.getAllByMark(Byte.parseByte(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Class> findByTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getAllByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getAllByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    protected List<ClassDto> modify(ClassDto classDto) {
        Class division = getByClassId(classDto.getId());
        division.setGrade(classDto.getGrade());
        division.setSign(classDto.getSign());
        division.setYear(division.getYear());
        division.setTeacherId(classDto.getTeacherId());
        List<ClassDto> answer = new ArrayList<>();
        answer.add(getDto(save(division)));
        return answer;
    }

    protected ClassDto getDto(@NotNull Class division) {
        return new ClassDto(division.getId(), division.getGrade(), division.getSign(), division.getYear(), division.getTeacherId());
    }

    protected List<ClassDto> getDtoList(List<Class> classList) {
        ArrayList<ClassDto> dtoList = new ArrayList<>();
        for (Class division : classList) {
            dtoList.add(getDto(division));
        }
        return dtoList;
    }

    protected Class getByDiary(@NotNull Diary diary) {
        return getByClassId(diary.getClassId());
    }

    protected List<Class> getClassesByDiaries(@NotNull List<Diary> diaries) {
        List<Class> classes = new ArrayList<>();
        for (Diary diary : diaries) {
            classes.add(getByDiary(diary));
        }
        return classes;
    }

    protected Class getByMark(@NotNull Mark mark) {
        return getByClassId((diaryCrudService.getById(mark.getDiaryId())).getClassId());
    }

    protected List<Class> getClassesByMarks(@NotNull List<Mark> marks) {
        List<Class> classes = new ArrayList<>();
        for (Mark mark : marks) {
            classes.add(getByMark(mark));
        }
        return classes;
    }

    protected List<Class> getByTeacher(@NotNull Teacher teacher) {
        return getAllByTeacherId(teacher.getId());
    }

    protected List<Class> getByTeachers(@NotNull List<Teacher> teachers) {
        List<Class> classes = new ArrayList<>();
        for (Teacher teacher : teachers) {
            classes.addAll(getByTeacher(teacher));
        }
        return classes;
    }

    protected List<Class> getByStudent(@NotNull Student student) {
        List<Class> classes = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getAllByStudentId(student.getId()))) {
            classes.add(getByDiary(diary));
        }
        return classes;
    }

    protected List<Class> getByStudents(@NotNull List<Student> students) {
        List<Class> classes = new ArrayList<>();
        for (Student student : students) {
            classes.addAll(getByStudent(student));
        }
        return classes;
    }

    protected List<Class> getBySubject(@NotNull Subject subject) {
        List<Class> classes = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getAllBySubjectId(subject.getId()))) {
            classes.add(getByMark(mark));
        }
        return classes;
    }

    protected List<Class> getBySubjects(@NotNull List<Subject> subjects) {
        List<Class> classes = new ArrayList<>();
        for (Subject subject : subjects) {
            classes.addAll(getBySubject(subject));
        }
        return classes;
    }


}
