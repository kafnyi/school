package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
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
public class MarkService extends MarkCrudService {

    private final DiaryCrudService diaryCrudService;
    private final SubjectCrudService subjectCrudService;
    private final StudentCrudService studentCrudService;
    private final ClassCrudService classCrudService;
    private final TeacherCrudService teacherCrudService;
    private List<Mark> rContent;

    public MarkService(MarkRepository markRepository, DiaryCrudService diaryCrudService, SubjectCrudService subjectCrudService, StudentCrudService studentCrudService, ClassCrudService classCrudService, TeacherCrudService teacherCrudService) {
        super(markRepository);
        this.diaryCrudService = diaryCrudService;
        this.subjectCrudService = subjectCrudService;
        this.studentCrudService = studentCrudService;
        this.classCrudService = classCrudService;
        this.teacherCrudService = teacherCrudService;
    }

    List<Mark> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return searchWithStudent(searchBy, value);
            }
            case Diary -> {
                return searchWithDiary(searchBy, value);
            }
            case Class -> {
                return searchWithClass(searchBy, value);
            }
            case Subject -> {
                return searchWithSubject(searchBy, value);
            }
            case Mark -> {
                return searchWithMark(searchBy, value);
            }
            case Teacher -> {
                return searchWithTeacher(searchBy, value);
            }
            default -> {
                return null;
            }
        }
    }

    List<Mark> searchWithStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
            case Name -> rContent = getByStudents(studentCrudService.getByName(value));
            case Date -> rContent = getByStudents(studentCrudService.getByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> searchWithDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value)));
            case StudentId -> rContent = getByDiaries(diaryCrudService.getByStudentId(Long.parseLong(value)));
            case ClassId -> rContent = getByDiaries(diaryCrudService.getByClassId(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> searchWithClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getByClass(classCrudService.getByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getByClasses(classCrudService.getByGrade(Short.parseShort(value)));
            case Sign -> rContent = getByClasses(classCrudService.getBySign(value.strip().charAt(0)));
            case Year -> rContent = getByClasses(classCrudService.getByYear(Year.parse(value)));
            case TeacherId -> rContent = getByClasses(classCrudService.getByTeacherId(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> searchWithSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent = getBySubject(subjectCrudService.getBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getBySubjects(subjectCrudService.getByName(value));
            case TeacherId -> rContent = getBySubjects(subjectCrudService.getByTeacherId(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> searchWithMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getByMarkId(Long.parseLong(value)));
            case DiaryId -> rContent = getByDiaryId(Integer.parseInt(value));
            case Date -> rContent = getByDate(value);
            case SubjectId -> rContent = getBySubjectid(Integer.parseInt(value));
            case Mark -> rContent = getByMark(Byte.parseByte(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> searchWithTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    protected List<Mark> getByDiary(@NotNull Diary diary) {
        return getByDiaryId(diary.getId());
    }

    protected List<Mark> getByDiaries(@NotNull List<Diary> diaries) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : diaries) {
            marks.addAll(getByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getByStudent(@NotNull Student student) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getByStudentId(student.getId()))) {
            marks.addAll(getByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getByStudents(@NotNull List<Student> students) {
        List<Mark> marks = new ArrayList<>();
        for (Student student : students) {
            marks.addAll(getByStudent(student));
        }
        return marks;
    }

    protected List<Mark> getByClass(@NotNull Class division) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getByClassId(division.getId()))) {
            marks.addAll(getByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getByClasses(@NotNull List<Class> classes) {
        List<Mark> marks = new ArrayList<>();
        for (Class division : classes) {
            marks.addAll(getByClass(division));
        }
        return marks;
    }

    public List<Mark> getBySubject(@NotNull Subject subject) {
        return getBySubjectid(subject.getId());
    }

    protected List<Mark> getBySubjects(@NotNull List<Subject> subjects) {
        List<Mark> marks = new ArrayList<>();
        for (Subject subject : subjects) {
            marks.addAll(getBySubject(subject));
        }
        return marks;
    }

    protected List<Mark> getByTeacher(@NotNull Teacher teacher) {
        List<Mark> marks = new ArrayList<>();
        for (Subject subject : new ArrayList<>(subjectCrudService.getByTeacherId(teacher.getId()))) {
            marks.addAll(getBySubject(subject));
        }
        return marks;
    }

    protected List<Mark> getByTeachers(@NotNull List<Teacher> teachers) {
        List<Mark> marks = new ArrayList<>();
        for (Teacher teacher : teachers) {
            marks.addAll(getByTeacher(teacher));
        }
        return marks;
    }
}
